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
    public class MovingAverageTrend
    {
        [DataMember]
        public int date { get; set; }

        [DataMember]
        public decimal ma { get; set; }
    }
}
