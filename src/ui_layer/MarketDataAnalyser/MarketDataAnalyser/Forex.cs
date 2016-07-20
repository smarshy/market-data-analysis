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
    public class Forex
    {
        [DataMember]
        public int indexKey { get; }

        [DataMember]
        public decimal closingPrice { get; set; }

        [DataMember]
        public int exchangeDate { get; set; }

        [DataMember]
        public decimal high { get; set; }

        [DataMember]
        public decimal low { get; set; }

        [DataMember]
        public decimal openingPrice { get; set; }

        [DataMember]
        public String ticker { get; set; }

        [DataMember]
        public int volume { get; set; }

        [DataMember]
        public decimal upArrow { get; set; }

        [DataMember]
        public Exchange exchange { get; set; }

        [DataMember]
        public String sector { get; set; }

        [DataMember]
        public String region { get; set; }
    }
}
