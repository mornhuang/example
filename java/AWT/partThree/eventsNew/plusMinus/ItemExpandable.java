public interface ItemExpandable {
	public Object[] getExpandedItems    ();
	public void     addExpandListener   (ItemExpandListener l);
	public void     removeExpandListener(ItemExpandListener l);
}
