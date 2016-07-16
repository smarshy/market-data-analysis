using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.IO;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Json;

namespace PipelineTest
{
    [DataContract]
    class Greetings
    {
        [DataMember]
        public string message { get; set; }

        public Greetings(string newMessage)
        {
            message = newMessage;
        }
    }
}
