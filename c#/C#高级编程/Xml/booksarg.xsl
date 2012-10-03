<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform"
                xmlns:bookUtil="urn:ProCSharp">
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
        <xsl:value-of select="bookUtil:ShowText()"/>
      </xsl:element>
    </xsl:element>   
	</xsl:template>

</xsl:stylesheet>
