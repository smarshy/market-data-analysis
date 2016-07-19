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
        
        DataContractJsonSerializer serializerNasdaq = new DataContractJsonSerializer(typeof(Nasdaq));

        private void ChangeSelection(object sender, SelectionChangedEventArgs e)
        {

            try {
                string getURL = "http://10.87.205.72:8080/MarketDataAnalyserWeb/rest/stocks/query";
                WebClient newWebClient = new WebClient();

                lblStockName.Content = lstStocks.SelectedItem;


                var newSerializer = new System.Web.Script.Serialization.JavaScriptSerializer();
                string receivedString = newWebClient.DownloadString(getURL + "?ticker=" + lstStocks.SelectedItem.ToString());
                Nasdaq newNasdaq = newSerializer.Deserialize<Nasdaq>(receivedString);


                lblStockName.Content = newNasdaq.ticker;
                lblOpeningPrice.Content = newNasdaq.openingPrice;
                lblClosingPrice.Content = newNasdaq.closingPrice;
                lblHigh.Content = newNasdaq.high;
                lblLow.Content = newNasdaq.low;
                lblVolume.Content = newNasdaq.volume;

                if (newNasdaq.upArrow)
                {
                    greenArrow.Visibility = Visibility.Visible;
                }
                else
                {
                    redArrow.Visibility = Visibility.Visible;
                }


                string getURLChart = "http://10.87.205.72:8080/MarketDataAnalyserWeb/rest/stocks/variation";

                string receivedStringChart = newWebClient.DownloadString(getURLChart + "/" +
                    lstStocks.SelectedItem.ToString() + "/20110103/20110113");

                List<Nasdaq> newNasdaqList = newSerializer.Deserialize<List<Nasdaq>>(receivedStringChart);
                List<KeyValuePair<int, decimal>> newKeyValuePairChart = new List<KeyValuePair<int, decimal>>();

                for (int i = 0; i < newNasdaqList.Count; i++)
                {
                    newKeyValuePairChart.Add(new KeyValuePair<int, decimal>(newNasdaqList[i].exchangeDate,
                        newNasdaqList[i].closingPrice));
                }


                LineSeries newLineSeries = new LineSeries();
                newLineSeries.Title = newNasdaq.ticker.ToString();
                newLineSeries.ItemsSource = newKeyValuePairChart;
                newLineSeries.DependentValuePath = "Value";
                newLineSeries.IndependentValuePath = "Key";
                lineChart.Series.Clear();
                lineChart.Series.Add(newLineSeries);

            }
            catch
            {
                MessageBox.Show("Server unavailable");
            }

        }

        private void ShowStockList(object sender, RoutedEventArgs e)
        {


            MainWindow newMainWindow = new MainWindow();

            for (int i = 0; i < LoginWindow.allStocks.Count; i++)
            {
                lstStocks.Items.Add(LoginWindow.allStocks[i]);
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

        private void ShowMainWindow(object sender, RoutedEventArgs e)
        {
            MainWindow newMainWindow = new MainWindow();
            newMainWindow.Show();
            this.Close();
        }

        private void ShowLoginWindow(object sender, RoutedEventArgs e)
        {
            LoginWindow newLoginWindow = new LoginWindow();
            newLoginWindow.Show();
            this.Close();
        }

        private void SnapGraph(object sender, RoutedEventArgs e)
        {
            try {

                string getURLChart = "http://10.87.205.72:8080/MarketDataAnalyserWeb/rest/stocks/variation";
                var newSerializer = new System.Web.Script.Serialization.JavaScriptSerializer();


                DateTime newFromDate = (DateTime)fromDate.SelectedDate;
                DateTime newToDate = (DateTime)toDate.SelectedDate;

                WebClient newWebClient = new WebClient();

                string receivedStringChart = newWebClient.DownloadString(getURLChart + "/" +
                    lstStocks.SelectedItem.ToString() + "/" + newFromDate.ToString("yyyyMMdd") + "/" + newToDate.ToString("yyyyMMdd"));


                List<Nasdaq> newNasdaqList = newSerializer.Deserialize<List<Nasdaq>>(receivedStringChart);
                List<KeyValuePair<int, decimal>> newKeyValuePairChart = new List<KeyValuePair<int, decimal>>();

                for (int i = 0; i < newNasdaqList.Count; i++)
                {
                    newKeyValuePairChart.Add(new KeyValuePair<int, decimal>(newNasdaqList[i].exchangeDate,
                        newNasdaqList[i].closingPrice));
                }


                LineSeries newLineSeries = new LineSeries();
                newLineSeries.Title = lstStocks.SelectedItem.ToString();
                newLineSeries.ItemsSource = newKeyValuePairChart;
                newLineSeries.DependentValuePath = "Value";
                newLineSeries.IndependentValuePath = "Key";
                lineChart.Series.Clear();
                lineChart.Series.Add(newLineSeries);

            }
            catch
            {
                MessageBox.Show("Server unavailable");
            }
         }

        private void FliterTheList(object sender, RoutedEventArgs e)
        {
            string getURL = "http://10.87.205.72:8080/MarketDataAnalyserWeb/rest/stocks";
            var newSerializer = new System.Web.Script.Serialization.JavaScriptSerializer();
            WebClient newWebClient = new WebClient();

            string receivedString = newWebClient.DownloadString(getURL + "/" +
                   comboBoxCountry.SelectedItem + "/" + comboBoxSector.SelectedItem + "/" + comboBoxExchange.SelectedItem);


            List<String> newStocksFilterList = newSerializer.Deserialize<List<String>>(receivedString);

            lstStocks.Items.Clear();
            for(int i=0 ; i<newStocksFilterList.Count ; i++)
            {
                lstStocks.Items.Add(newStocksFilterList[i]);
            }


        }
    }
}
