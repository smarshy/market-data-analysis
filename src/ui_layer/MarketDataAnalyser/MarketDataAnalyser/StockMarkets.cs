using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Json;
using System.Net;
using System.Windows.Controls.DataVisualization.Charting;

namespace MarketDataAnalyser
{
    [DataContract]
    public class StockMarkets
    {
        [DataMember]
        public List<String> nasdaqStockList { get; set; }

        [DataMember]
        public List<String> liffeStockList { get; set; }

        [DataMember]
        public List<String> forexStockList { get; set; }

        [DataMember]
        public Nasdaq defaultStock { get; set; }
    }
}
