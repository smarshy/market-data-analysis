using System;
using System.Collections.Generic;
using System.Linq;
using System.Text;
using System.Threading.Tasks;
using System.Windows;
using System.Windows.Controls;
using System.Windows.Data;
using System.Windows.Documents;
using System.Windows.Input;
using System.Windows.Media;
using System.Windows.Media.Imaging;
using System.Windows.Navigation;
using System.Windows.Shapes;
using System.IO;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Json;
using System.Net;


namespace PipelineTest
{
    /// <summary>
    /// Interaction logic for MainWindow.xaml
    /// </summary>
    public partial class MainWindow : Window
    {
        public MainWindow()
        {
            InitializeComponent();
        }
        public string fileName = "TestFile.txt";

        public DataContractJsonSerializer serializer = new DataContractJsonSerializer(typeof(string));

        private void SubmitName(object sender, RoutedEventArgs e)
        {
            string submittedName = txtName.Text;
            Greetings newGreetings = new Greetings(submittedName);
            //  FileStream newFileStream = new FileStream(fileName, FileMode.Create);

            MemoryStream mStream = new MemoryStream();
            serializer.WriteObject(mStream, newGreetings);
            mStream.Seek(0, SeekOrigin.Begin);
            byte[] buffer = new byte[mStream.Length];
            mStream.Read(buffer, 0, (int)mStream.Length);
            string result = System.Text.Encoding.UTF8.GetString(buffer);



            // serializer.WriteObject(newFileStream, submittedName);
            // newFileStream.Close();

            //  StreamReader newStreamReader = new StreamReader(fileName);
            // string newMessage = (string)serializer.ReadObject(newStreamReader.BaseStream);
            WebClient newWebClient = new WebClient();

            newWebClient.Headers.Add(HttpRequestHeader.ContentType, "application/json");
            newWebClient.UploadString(new Uri("http://192.168.85.52:8080/MarketDataAnalyserWeb/rest/users"),
                "POST", result);

            string receivedMessage = newWebClient.DownloadString("http://192.168.85.52:8080/MarketDataAnalyserWeb/rest/users");

            MessageBox.Show(receivedMessage);

        }

        public Stream GenerateStreamFromString(string newString)
        {
            MemoryStream stream = new MemoryStream();
            StreamWriter writer = new StreamWriter(stream);
            writer.Write(newString);
            writer.Flush();
            stream.Position = 0;
            return stream;
        }
    }
}
