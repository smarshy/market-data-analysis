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
    class Nasdaq
    {
        //private BigDecimal closingPrice;
        //private int exchangeDate;
        //private BigDecimal high;
        //private BigDecimal low;
        //private BigDecimal openingPrice;
        //private String ticker;
        //private int volume;

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
        public bool upArrow { get; set; }

        [DataMember]
        public Exchange exchange { get; set; }

        //public Nasdaq(decimal newClosingPrice,int newExchangeDate,
        //    decimal newHigh,decimal newLow,decimal newOpeningPrice,String newTicker,int newVolume,bool newArrow,Exchange newExchange)
        //{
        //    closingPrice = newClosingPrice;
        //    exchangeDate = newExchangeDate;
        //    high = newHigh;
        //    low = newLow;
        //    openingPrice = newOpeningPrice;
        //    ticker = newTicker;
        //    volume = newVolume;
        //    upArrow = newArrow;
        //    exchange = new Exchange(newExchange.marketID, newExchange.marketName);
        //}


            public Nasdaq() { }

        public override string ToString()
        {
            return ("Opening price: " + openingPrice + "\nClosing price: " + 
                closingPrice + "\nHigh: " + high + "\nLow: " + low + "\nVolume: " + volume);
        }
    }
}
