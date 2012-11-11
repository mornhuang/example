<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:msxsl="urn:schemas-microsoft-com:xslt"
                xmlns:user="http://wrox.com">
 
  <msxsl:script language="C#" implements-prefix="user">
  
    string ShowText()
      {
        
        return "This came from the ShowText method!";
        
      }

  </msxsl:script>
                
  <xsl:output method="xml" indent="yes"/>
	<xsl:template match="/">
  <xsl:element name="books">
	  <xsl:apply-templates/>
  </xsl:element>  
	</xsl:template>

  <xsl:template match="bookstore">
			<xsl:apply-templates select="book"/>
	</xsl:template>

	<xsl:template match="book">
    <xsl:element name="discbook">
      <xsl:element name="booktitle">
   	    <xsl:value-of select="title"/>
      </xsl:element>
      <xsl:element name="showtext">
        <xsl:value-of select="user:ShowText()"/>
      </xsl:element>
    </xsl:element>   
	</xsl:template>

</xsl:stylesheet>
