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
using System.Windows.Shapes;
using System.IO;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Json;
using System.Net;
using System.Windows.Controls.DataVisualization.Charting;


namespace MarketDataAnalyser
{
    /// <summary>
    /// Interaction logic for StockListWindow.xaml
    /// </summary>
    public partial class StockListWindow : Window
    {
        public StockListWindow()
        {
            InitializeComponent();
        }

        //public static  List<String> allStocks = new List<String>();
        //DataContractJsonSerializer serializerListString = new DataContractJsonSerializer(typeof(List<String>));
        DataContractJsonSerializer serializerNasdaq = new DataContractJsonSerializer(typeof(Nasdaq));

        private void ChangeSelection(object sender, SelectionChangedEventArgs e)
        {
            //string getURL = "http://192.168.239.51:8080/MarketDataAnalyserWeb/rest/stocks/query";
            //WebClient newWebClient = new WebClient();

            //lblStockName.Content = lstStocks.SelectedItem;

            ////newWebClient.Headers.Add(HttpRequestHeader.ContentType, "text/plain");
            ////newWebClient.UploadString(new Uri(getURL), "POST", lstStocks.SelectedItem.ToString());

            //Stream receivedStream = newWebClient.OpenRead(getURL + "?ticker=" + lstStocks.SelectedItem.ToString());
            //Nasdaq newNasdaq = (Nasdaq)serializerNasdaq.ReadObject(receivedStream);
            ////stackPanelStockDetails.(newNasdaq.ToString());
            //MessageBox.Show(newNasdaq.ToString());

        }

        private void ShowStockList(object sender, RoutedEventArgs e)
        {

            //string getURL = "http://192.168.239.51:8080/MarketDataAnalyserWeb/rest/stocks";
            //WebClient newWebClient = new WebClient();


            //Stream receivedStream = newWebClient.OpenRead(getURL);
            //allStocks = (List<String>)serializerListString.ReadObject(receivedStream);

            MainWindow newMainWindow = new MainWindow();

            for (int i = 0; i < MainWindow.allStocks.Count; i++)
            {
                lstStocks.Items.Add(MainWindow.allStocks[i]);
            }

        }


        private void ShowCountry(object sender, RoutedEventArgs e)
        {
            comboBoxCountry.Items.Add("All regions");
            comboBoxCountry.Items.Add("EMEA");
            comboBoxCountry.Items.Add("NAM");
            comboBoxCountry.Items.Add("APAC");
            comboBoxCountry.Items.Add("Latin America");
        }

        private void ShowSectors(object sender, RoutedEventArgs e)
        {
            comboBoxSector.Items.Add("All sectors");
            comboBoxSector.Items.Add("Automotive");
            comboBoxSector.Items.Add("Banking/Finance");
            comboBoxSector.Items.Add("Engineering");
            comboBoxSector.Items.Add("Oil and Gas");
            comboBoxSector.Items.Add("Technology");
            comboBoxSector.Items.Add("Pharmaceuticals");
         
        }

        private void ShowExchange(object sender, RoutedEventArgs e)
        {
            comboBoxExchange.Items.Add("NASDAQ");
            comboBoxExchange.Items.Add("NYSE");
            comboBoxExchange.Items.Add("LIFFE");

        }
    }
}
