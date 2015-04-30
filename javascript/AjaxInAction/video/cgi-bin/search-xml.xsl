<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
<xsl:template match="/">
    <table id="results-table">
      <tr>
        <th align="left">Name</th>
        <th align="left">Size</th>
        <th align="left">Description</th>
      </tr>
      <xsl:for-each select="planets/planet">
        <tr>
          <td class="name"><xsl:value-of select="name"/></td>
          <td class="size"><xsl:value-of select="size"/></td>
          <td class="description"><xsl:value-of select="description"/></td>
        </tr>
      </xsl:for-each>
    </table>
</xsl:template>
</xsl:stylesheet>
