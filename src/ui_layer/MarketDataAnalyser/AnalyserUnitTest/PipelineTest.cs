using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Json;
using System.Net;
using System.Web;
using System.Web.Script;


namespace MarketDataAnalyser
{
    [DataContract]
    public class PipelineTest
    {
        private string v;

        [DataMember]
        public string Name { get; set; }

        public PipelineTest(string newName)
        {
            Name = newName;
        }



        public string TestTheWiring()
        {
            string getURL = "";
            WebClient newWebClient = new WebClient();
            newWebClient.Proxy = null;

            string receivedString = newWebClient.DownloadString(getURL + "/" + Name);

            return receivedString;
        }

        public string TestTheStockName()
        {
            string getURL = "http://10.87.198.158:8080/MarketDataAnalyserWeb/rest/stocks/NASDAQ/details";
            WebClient newWebClient = new WebClient();
            newWebClient.Proxy = null;

            string receivedString = newWebClient.DownloadString(getURL + "/" + Name);
            var newSerializer = new System.Web.Script.Serialization.JavaScriptSerializer();
          //  string receivedString = newWebClient.DownloadString(getURL + "/" + lstStocks.SelectedItem.ToString());
            Nasdaq newNasdaq = newSerializer.Deserialize<Nasdaq>(receivedString);
            return newNasdaq.ticker.ToString();
        }
    }
}
