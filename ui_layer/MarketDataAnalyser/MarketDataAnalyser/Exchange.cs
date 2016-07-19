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
    class Exchange
    {
        [DataMember]
        public int marketID { get; set; }

        [DataMember]
        public String marketName { get; set; }

        //public Exchange(int newID,String newName)
        //{
        //    marketID = newID;
        //    marketName = newName;
        //}

        public Exchange() { }
    }
}
