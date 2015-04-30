<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
    <table id="table1">
      <tr>
        <th align="left">Company</th>
        <th align="left">Contact</th>
        <th align="left">Country</th>
        <th align="left">Phone</th>        
      </tr>
      <xsl:for-each select="phonebook/entry">
      <tr>
        <td><xsl:value-of select="company"/></td>
        <td><xsl:value-of select="contact"/></td>
        <td><xsl:value-of select="country"/></td>
        <td><xsl:value-of select="phone"/></td>
      </tr>
      </xsl:for-each>
    </table>
</xsl:template>
</xsl:stylesheet>