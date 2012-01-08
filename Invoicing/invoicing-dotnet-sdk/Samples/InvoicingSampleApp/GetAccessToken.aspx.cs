using System;
using System.Data;
using System.Configuration;
using System.Collections;
using System.Web;
using System.Web.Security;
using System.Web.UI;
using System.Web.UI.WebControls;
using System.Web.UI.WebControls.WebParts;
using System.Web.UI.HtmlControls;

namespace InvoicingSampleApp
{
    public partial class GetAccessToken : System.Web.UI.Page
    {
        

        protected void Page_Load(object sender, EventArgs e)
        {
            this.txtrequest_token.Value = Request.QueryString["request_token"].ToString();
            this.txtverification_code.Value = Request.QueryString["verification_code"].ToString();
            this.source.Value = Request.Params["source"];
        }

        protected void btnGetAccessToken_Click(object sender, EventArgs e)
        {

        }
    }
}