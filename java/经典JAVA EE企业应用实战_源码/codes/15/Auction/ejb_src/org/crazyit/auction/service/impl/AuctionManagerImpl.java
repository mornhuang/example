package org.crazyit.auction.service.impl;

import org.apache.log4j.Logger;

import java.util.*;
import javax.ejb.*;
import javax.annotation.*;
import javax.jms.*;

import org.crazyit.auction.eao.*;
import org.crazyit.auction.model.*;
import org.crazyit.auction.exception.AuctionException;
import org.crazyit.auction.service.AuctionManager;

/**
 * Description:
 * <br/>网站: <a href="http://www.crazyit.org">疯狂Java联盟</a> 
 * <br/>Copyright (C), 2001-2010, Yeeku.H.Lee
 * <br/>This program is protected by copyright laws.
 * <br/>Program Name:
 * <br/>Date:
 * @author  Yeeku.H.Lee kongyeeku@163.com
 * @version  1.0
 */
//定义容器管理事务的配置
@TransactionManagement(TransactionManagementType.CONTAINER)
@TransactionAttribute(TransactionAttributeType.REQUIRED)
@Stateless(name="auctionManager")
public class AuctionManagerImpl implements AuctionManager
{
	static Logger log = Logger.getLogger(
		AuctionManagerImpl.class.getName());
	//以下是该业务逻辑组件所依赖的EAO组件
	@EJB(beanName="auctionUserEao")
	private AuctionUserEao userEao;
	@EJB(beanName="bidEao")
	private BidEao bidEao;
	@EJB(beanName="itemEao")
	private ItemEao itemEao;
	@EJB(beanName="kindEao")
	private KindEao kindEao;
	@EJB(beanName="stateEao")
	private StateEao stateEao;
	//下面两个资源是发送JMS消息所需的资源
	@Resource(mappedName="AuctionQueue")
	private Destination dest;
	@Resource(mappedName="ConnectionFactory")
	private ConnectionFactory connFactory;
	/**
	 * 根据赢取者查询物品
	 * @param winerId 赢取者的ID
	 * @return 赢取者获得的全部物品
	 */
	public List<Item> getItemByWiner(Integer winerId) throws AuctionException
	{
		try
		{
			return itemEao.findItemByWiner(winerId);
		}
		catch (Exception e)
		{
			log.debug(e.getMessage());
			throw new AuctionException("查询用户所赢取的物品出现异常,请重试");
		}
	}

	/**
	 * 查询流拍的全部物品
	 * @return 全部流拍物品
	 */
	public List<Item> getFailItems() throws AuctionException
	{
		try
		{
			return itemEao.findItemByState(3);
		}
		catch (Exception e)
		{
			log.debug(e.getMessage());
			throw new AuctionException("查询流拍物品出现异常,请重试");
		}
	}

	/**
	 * 根据用户名，密码验证登录是否成功
	 * @param username 登录的用户名
 	 * @param pass 登录的密码
	 * @return 登录成功返回用户ID，否则返回-1
	 */
	public int validLogin(String username , String pass) throws AuctionException
	{
		try
		{
			AuctionUser u = userEao.findUserByNameAndPass(username , pass);
			if (u != null)
			{
				return u.getId();
			}
			return -1;
		}
		catch (Exception e)
		{
			log.debug(e.getMessage());
			throw new AuctionException("处理用户登录出现异常,请重试");
		}
	}

	/**
	 * 查询用户的全部出价
	 * @param userId 竞价用户的ID
	 * @return 用户的全部出价
	 */
	public List<Bid> getBidByUser(Integer userId) throws AuctionException
	{
		try
		{
			return bidEao.findByUser(userId);
		}
		catch (Exception e)
		{
			log.debug(e.getMessage());
			throw new AuctionException("浏览用户的全部竞价出现异常,请重试");
		}
	}

	/**
	 * 根据用户查找目前仍在拍卖中的全部物品
	 * @param userId 所属者的ID
	 * @return 属于当前用户的、处于拍卖中的全部物品。
	 */
	public List<Item> getItemsByOwner(Integer userId) throws AuctionException
	{
		try
		{
			return itemEao.findItemByOwner(userId);
		}
		catch (Exception e)
		{
			log.debug(e.getMessage());
			throw new AuctionException("查询用户所有的物品出现异常,请重新");
		}
	}

	/**
	 * 查询全部种类
	 * @return 系统中全部全部种类
	 */   
	public List<Kind> getAllKind() throws AuctionException
	{
		try
		{
			return kindEao.findAll();
		}
		catch (Exception e)
		{
			log.debug(e.getMessage());
			throw new AuctionException("查询全部种类出现异常,请重试");
		}
	}

	/**
	* 添加物品
	* @param name 物品名称
	* @param desc 物品描述
	* @param remark 物品备注
	* @param avail 有效天数
	* @param kind 物品种类
	* @param userId 添加者的ID
	* @return 新增物品的主键
	*/
	public int addItem(String name , String desc , String remark ,
		double initPrice , int avail , int kindId , Integer userId)
		throws AuctionException
	{
		try
		{
			Kind k = kindEao.get(Kind.class , kindId);
			AuctionUser owner = userEao.get(AuctionUser.class , userId);
			//创建Item对象
			Item item = new Item();
			item.setItemName(name);
			item.setItemDesc(desc);
			item.setItemRemark(remark);
			item.setAddtime(new Date());
			Calendar c = Calendar.getInstance();
			c.add(Calendar.DATE , avail);
			item.setEndtime(c.getTime());
			item.setInitPrice(initPrice);
			item.setMaxPrice(initPrice);
			item.setItemState(stateEao.get(State.class , 1));
			item.setKind(k);
			item.setOwner(owner);
			//持久化Item对象
			itemEao.save(item);
			return item.getId();
		}
		catch (Exception e)
		{
			log.debug(e.getMessage());
			throw new AuctionException("添加物品出现异常,请重试");
		}
	}

	/**
	 * 添加种类
	 * @param name 种类名称
	 * @param desc 种类描述
	 * @return 新增种类的主键
	 */ 
	public int addKind(String name , String desc)
		throws AuctionException
	{
		try
		{
			Kind k = new Kind();
			k.setKindName(name);
			k.setKindDesc(desc);
			kindEao.save(k);
			return k.getId();
		}
		catch (Exception e)
		{
			log.debug(e.getMessage());
			throw new AuctionException("添加种类出现异常,请重试");
		}
	}

	/**
	 * 根据产品分类，获取处于拍卖中的全部物品
	 * @param kindId 种类id;
	 * @return 该类的全部产品
	 */
	public List<Item> getItemsByKind(int kindId) throws AuctionException
	{
		try
		{
			return itemEao.findItemByKind(kindId);
		}
		catch (Exception e)
		{
			log.debug(e.getMessage());
			throw new AuctionException("根据种类获取物品出现异常,请重试");
		}
	}

	/**
	 * 根据种类id获取种类名
	 * @param kindId 种类id;
	 * @return 该种类的名称
	 */
	public String getKind(int kindId) throws AuctionException
	{
		try
		{
			Kind  k = kindEao.get(Kind.class , kindId);
			if (k != null)
			{
				return k.getKindName();
			}
			return null;
		}
		catch (Exception ex)
		{
			log.debug(ex.getMessage());
			throw new AuctionException("根据种类id获取种类名称出现异常,请重试");
		}
	}

	/**
	 * 根据物品id，获取物品
	 * @param itemId 物品id;
	 * @return 指定id对应的物品
	 */
	public Item getItem(int itemId)
		throws AuctionException
	{
		try
		{
			return itemEao.get(Item.class, itemId);
		}
		catch (Exception ex)
		{
			log.debug(ex.getMessage());
			throw new AuctionException("根据物品id获取物品详细信息出现异常,请重试");
		}
	}

	/**
	 * 增加新的竞价，并对竞价用户发邮件通知
	 * @param itemId 物品id;
	 * @param bidPrice 竞价价格
	 * @param userId 竞价用户的ID
	 * @return 返回新增竞价记录的ID
	 */
	public int addBid(int itemId , double bidPrice , Integer userId)
		throws AuctionException
	{
		try
		{
			AuctionUser au = userEao.get(AuctionUser.class , userId);
			Item item = itemEao.get(Item.class , itemId);
			if (bidPrice > item.getMaxPrice())
			{
				item.setMaxPrice(bidPrice);
				itemEao.save(item);
			}
			//初始化Bid对象
			Bid bid = new Bid();
			bid.setBidItem(item);
			bid.setBidUser(au);
			bid.setBidDate(new Date());
			bid.setBidPrice(bidPrice);
			//持久化Bid对象
			bidEao.save(bid);

			//连接工厂创建连接
			Connection conn = connFactory.createConnection();
			//JMS连接创建JMS会话
			Session session = conn.createSession(false/*不是事务性会话*/
				, Session.AUTO_ACKNOWLEDGE);
			//JMS会话创建消息生产者
			MessageProducer sender = session.createProducer(dest);
			//设置消息生产者生产出来的消息的传递模式、有效时间。
			sender.setDeliveryMode(DeliveryMode.PERSISTENT);
			sender.setTimeToLive(20000);
			//通过JMS会话创建一个MapMessage
			MapMessage msg = session.createMapMessage();
			//设置消息内容
			msg.setString("mailTo" , au.getEmail());
			msg.setString("bidUser" , au.getUsername());
			msg.setString("itemName" , item.getItemName());
			//发送消息
			sender.send(msg);
			//关闭资源
			session.close();
			conn.close();

			return bid.getId();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			log.debug(ex.getMessage());
			throw new AuctionException("处理用户竞价出现异常,请重试");
		}
	}

	/**
	 * 根据时间来修改物品的状态、赢取者
	 */
	public void updateWiner()
		throws AuctionException
	{
		try
		{
			List<Item> itemList = itemEao.findItemByState(1);
			for (int i = 0 ; i < itemList.size() ; i++ )
			{
				Item item = itemList.get(i);
				if (!item.getEndtime().after(new Date()))
				{
					//根据指定物品和最高竞价来查询用户
					AuctionUser au = bidEao.findUserByItemAndPrice(
						item.getId() , item.getMaxPrice());
					//如果该物品的最高竞价者不为null
					if (au != null)
					{
						//将该竞价者设为赢取者
						item.setWiner(au);
						//修改物品的状态成为“被赢取”
						item.setItemState(stateEao.get(State.class , 2));
						itemEao.save(item);
					}
					else
					{
						//设置该物品的状态为“流拍”
						item.setItemState(stateEao.get(State.class , 3));
						itemEao.save(item);
					}
				}
			}
		}
		catch (Exception ex)
		{
			log.debug(ex.getMessage());
			throw new AuctionException("检查物品是否超过竞拍时间出现异常,请重试");
		}
	}
}