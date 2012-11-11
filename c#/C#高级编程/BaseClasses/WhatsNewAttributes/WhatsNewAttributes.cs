using System;

namespace Wrox.ProCSharp.WhatsNewAttributes
{
   [AttributeUsage(
      AttributeTargets.Class | AttributeTargets.Method,
      AllowMultiple=true, Inherited=false)]
   public class LastModifiedAttribute : Attribute
   {
      private DateTime dateModified;
      private string changes;
      private string issues;

      public LastModifiedAttribute(string dateModified, string changes)
      {
         this.dateModified = DateTime.Parse(dateModified);
         this.changes = changes;
      }

      public DateTime DateModified
      {
         get
         {
            return dateModified;
         }
      }

      public string Changes
      {
         get
         {
            return changes;
         }
      }

      public string Issues
      {
         get
         {
            return issues;
         }
         set
         {
            issues = value;
         }
      }
   }

   [AttributeUsage(AttributeTargets.Assembly)]
   public class SupportsWhatsNewAttribute : Attribute
   {
   }
}