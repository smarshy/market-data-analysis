using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Json;
using System.Net;

namespace MarketDataAnalyser
{
    [DataContract]
    class CompareStocks
    {
        [DataMember]
        public Nasdaq stock1;

        [DataMember]
        public Nasdaq stock2;

        [DataMember]
        public Dictionary<int,decimal> mapStock1;

        [DataMember]
        public Dictionary<int,decimal> mapStock2;

    }
}
