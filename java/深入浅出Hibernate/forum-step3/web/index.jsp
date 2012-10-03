<frameset framespacing="0" border="true" rows="84,*" frameborder="0">
  <frame name="banner" scrolling="no"  src="banner.jsp" border='1'>
  <frameset name="content" cols="153,*" border='1'>
    <frame name="boardTree"  src="listBoard.action" scrolling="no" >
      <frameset name="content" rows="200,*" border='1'>
        <frame name="ArticleTree" src="welcome.jsp" scrolling="yes" border='1'>
        <frame name="ArticleDetail" src="empty.jsp" scrolling="yes" border='1'>
      </frameset>
  </frameset>
  <noframes>
  <body>
  <p>This page uses frames, but your browser doesn't support them.</p>
  </body>
  </noframes>
</frameset>
