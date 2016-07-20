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
using System.Globalization;



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

            try
            {

                if (lstStocks.SelectedIndex == -1)
                {
                    return;
                }

                else
                {
                    greenArrow.Visibility = Visibility.Hidden;
                    redArrow.Visibility = Visibility.Hidden;


                    if (comboBoxExchange.SelectedItem.Equals("Nasdaq"))
                    {
                        string getURL = "http://10.87.205.72:8080/MarketDataAnalyserWeb/rest/stocks/NASDAQ/details";
                        WebClient newWebClient = new WebClient();
                        newWebClient.Proxy = null;


                        lblStockName.Content = lstStocks.SelectedItem;


                        var newSerializer = new System.Web.Script.Serialization.JavaScriptSerializer();
                        string receivedString = newWebClient.DownloadString(getURL + "/" + lstStocks.SelectedItem.ToString());
                        Nasdaq newNasdaq = newSerializer.Deserialize<Nasdaq>(receivedString);


                        lblStockName.Content = newNasdaq.ticker;
                        lblOpeningPrice.Content = newNasdaq.openingPrice;
                        lblClosingPrice.Content = newNasdaq.closingPrice;
                        lblHigh.Content = newNasdaq.high;
                        lblLow.Content = newNasdaq.low;
                        lblVolume.Content = newNasdaq.volume;
                        lblTickerValue.Content = Math.Abs(newNasdaq.upArrow); ;

                        if (Decimal.Compare(newNasdaq.upArrow, 0) >= 0)
                        {
                            greenArrow.Visibility = Visibility.Visible;
                        }
                        else
                        {
                            redArrow.Visibility = Visibility.Visible;
                        }


                        string getURLChart = "http://10.87.205.72:8080/MarketDataAnalyserWeb/rest/stocks/NASDAQ/variation";

                        string receivedStringChart = newWebClient.DownloadString(getURLChart + "/" +
                            lstStocks.SelectedItem.ToString());

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
                        lineChart.Title = "Price Trend";

                    }
                    else if (comboBoxExchange.SelectedItem.Equals("Forex"))
                    {
                        string getURL = "http://10.87.205.72:8080/MarketDataAnalyserWeb/rest/stocks/FOREX/details";
                        WebClient newWebClient = new WebClient();
                        newWebClient.Proxy = null;


                        lblStockName.Content = lstStocks.SelectedItem;


                        var newSerializer = new System.Web.Script.Serialization.JavaScriptSerializer();
                        string receivedString = newWebClient.DownloadString(getURL + "/" + lstStocks.SelectedItem.ToString());
                        Forex newNasdaq = newSerializer.Deserialize<Forex>(receivedString);


                        lblStockName.Content = newNasdaq.ticker;
                        lblOpeningPrice.Content = newNasdaq.openingPrice;
                        lblClosingPrice.Content = newNasdaq.closingPrice;
                        lblHigh.Content = newNasdaq.high;
                        lblLow.Content = newNasdaq.low;
                        lblVolume.Content = newNasdaq.volume;
                        lblTickerValue.Content = Math.Abs(newNasdaq.upArrow); ;

                        if (Decimal.Compare(newNasdaq.upArrow, 0) >= 0)
                        {
                            greenArrow.Visibility = Visibility.Visible;
                        }
                        else
                        {
                            redArrow.Visibility = Visibility.Visible;
                        }


                        string getURLChart = "http://10.87.205.72:8080/MarketDataAnalyserWeb/rest/stocks/FOREX/variation";

                        string receivedStringChart = newWebClient.DownloadString(getURLChart + "/" +
                            lstStocks.SelectedItem.ToString());

                        List<Forex> newNasdaqList = newSerializer.Deserialize<List<Forex>>(receivedStringChart);
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
                        lineChart.Title = "Price Trend";
                    }
                    else
                    {
                        string getURL = "http://10.87.205.72:8080/MarketDataAnalyserWeb/rest/stocks/LIFFE/details";
                        WebClient newWebClient = new WebClient();
                        newWebClient.Proxy = null;


                        lblStockName.Content = lstStocks.SelectedItem;


                        var newSerializer = new System.Web.Script.Serialization.JavaScriptSerializer();
                        string receivedString = newWebClient.DownloadString(getURL + "/" + lstStocks.SelectedItem.ToString());
                        Liffe newNasdaq = newSerializer.Deserialize<Liffe>(receivedString);


                        lblStockName.Content = newNasdaq.ticker;
                        lblOpeningPrice.Content = newNasdaq.openingPrice;
                        lblClosingPrice.Content = newNasdaq.closingPrice;
                        lblHigh.Content = newNasdaq.high;
                        lblLow.Content = newNasdaq.low;
                        lblVolume.Content = newNasdaq.volume;
                        lblTickerValue.Content = Math.Abs(newNasdaq.upArrow); ;

                        if (Decimal.Compare(newNasdaq.upArrow, 0) >= 0)
                        {
                            greenArrow.Visibility = Visibility.Visible;
                        }
                        else
                        {
                            redArrow.Visibility = Visibility.Visible;
                        }


                        string getURLChart = "http://10.87.205.72:8080/MarketDataAnalyserWeb/rest/stocks/LIFFE/variation";

                        string receivedStringChart = newWebClient.DownloadString(getURLChart + "/" +
                            lstStocks.SelectedItem.ToString());

                        List<Liffe> newNasdaqList = newSerializer.Deserialize<List<Liffe>>(receivedStringChart);
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
                        lineChart.Title = "Price Trend";
                    }
                }
            }
            catch
            {
                MessageBox.Show("Server unavailable. Please check the connection.");
            }

        }


        private void ShowStockList(object sender, RoutedEventArgs e)
        {


            MainWindow newMainWindow = new MainWindow();

            for (int i = 0; i < LoginWindow.allStocks.Count; i++)
            {
                lstStocks.Items.Add(LoginWindow.allStocks[i]);
            }

            fromDate.DisplayDateStart = DateTime.ParseExact("01/01/2011", "dd/MM/yyyy", CultureInfo.InvariantCulture);
            toDate.DisplayDateStart = DateTime.ParseExact("01/01/2011", "dd/MM/yyyy", CultureInfo.InvariantCulture);
            fromDate.DisplayDateEnd = DateTime.ParseExact("31/12/2012", "dd/MM/yyyy", CultureInfo.InvariantCulture);
            toDate.DisplayDateEnd = DateTime.ParseExact("31/12/2012", "dd/MM/yyyy", CultureInfo.InvariantCulture);
            fromDate.DisplayDate = DateTime.ParseExact("01/01/2011", "dd/MM/yyyy", CultureInfo.InvariantCulture);
            toDate.DisplayDate = DateTime.ParseExact("01/01/2011", "dd/MM/yyyy", CultureInfo.InvariantCulture);

        }


        private void ShowCountry(object sender, RoutedEventArgs e)
        {
            comboBoxCountry.Items.Add("All Regions");
            comboBoxCountry.Items.Add("EMEA");
            comboBoxCountry.Items.Add("NAM");
            comboBoxCountry.Items.Add("APAC");
            comboBoxCountry.Items.Add("Latin America");
        }

        private void ShowSectors(object sender, RoutedEventArgs e)
        {
            comboBoxSector.Items.Add("All Sectors");
            comboBoxSector.Items.Add("Automotive");
            comboBoxSector.Items.Add("Banking and Finance");
            comboBoxSector.Items.Add("Engineering");
            comboBoxSector.Items.Add("Oil and Gas");
            comboBoxSector.Items.Add("Technology");
            comboBoxSector.Items.Add("Pharmaceuticals");

        }

        private void ShowExchange(object sender, RoutedEventArgs e)
        {
            comboBoxExchange.Items.Add("Nasdaq");
            comboBoxExchange.Items.Add("Forex");
            comboBoxExchange.Items.Add("Liffe");

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
            if (fromDate != null && toDate != null)
            {
                if (DateTime.Compare((DateTime)fromDate.SelectedDate, (DateTime)toDate.SelectedDate) < 1)
                {
                    try
                    {
                        if (comboBoxExchange.SelectedItem.Equals("Nasdaq"))
                        {
                            string getURLChart = "http://10.87.205.72:8080/MarketDataAnalyserWeb/rest/stocks/NASDAQ/variation";
                            var newSerializer = new System.Web.Script.Serialization.JavaScriptSerializer();


                            DateTime newFromDate = (DateTime)fromDate.SelectedDate;
                            DateTime newToDate = (DateTime)toDate.SelectedDate;

                            WebClient newWebClient = new WebClient();
                            newWebClient.Proxy = null;

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
                            lineChart.Title = "Price Trend";

                        }
                        else if (comboBoxExchange.SelectedItem.Equals("Forex"))
                        {
                            string getURLChart = "http://10.87.205.72:8080/MarketDataAnalyserWeb/rest/stocks/FOREX/variation";
                            var newSerializer = new System.Web.Script.Serialization.JavaScriptSerializer();


                            DateTime newFromDate = (DateTime)fromDate.SelectedDate;
                            DateTime newToDate = (DateTime)toDate.SelectedDate;

                            WebClient newWebClient = new WebClient();
                            newWebClient.Proxy = null;

                            string receivedStringChart = newWebClient.DownloadString(getURLChart + "/" +
                                lstStocks.SelectedItem.ToString() + "/" + newFromDate.ToString("yyyyMMdd") + "/" + newToDate.ToString("yyyyMMdd"));


                            List<Forex> newNasdaqList = newSerializer.Deserialize<List<Forex>>(receivedStringChart);
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

                        else
                        {
                            string getURLChart = "http://10.87.205.72:8080/MarketDataAnalyserWeb/rest/stocks/LIFFE/variation";
                            var newSerializer = new System.Web.Script.Serialization.JavaScriptSerializer();


                            DateTime newFromDate = (DateTime)fromDate.SelectedDate;
                            DateTime newToDate = (DateTime)toDate.SelectedDate;

                            WebClient newWebClient = new WebClient();
                            newWebClient.Proxy = null;

                            string receivedStringChart = newWebClient.DownloadString(getURLChart + "/" +
                                lstStocks.SelectedItem.ToString() + "/" + newFromDate.ToString("yyyyMMdd") + "/" + newToDate.ToString("yyyyMMdd"));


                            List<Liffe> newNasdaqList = newSerializer.Deserialize<List<Liffe>>(receivedStringChart);
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
                            lineChart.Title = "Price Trend";
                        }
                    }
                    catch
                    {
                        MessageBox.Show("Server unavailable. Please check the connection.");
                    }
                }
                else
                {
                    MessageBox.Show("To date is greater than from date. PLease select correct values. ");
                }
            }
            else
            {
                MessageBox.Show("Please enter the dates.");
            }
        }

        private void FliterTheList(object sender, RoutedEventArgs e)
        {
            try
            {

                string getURL = "http://10.87.205.72:8080/MarketDataAnalyserWeb/rest/stocks";
                var newSerializer = new System.Web.Script.Serialization.JavaScriptSerializer();
                WebClient newWebClient = new WebClient();
                newWebClient.Proxy = null;

                string receivedString = newWebClient.DownloadString(getURL + "/" +
                       comboBoxCountry.SelectedItem + "/" + comboBoxSector.SelectedItem + "/" + comboBoxExchange.SelectedItem);


                List<String> newStocksFilterList = newSerializer.Deserialize<List<String>>(receivedString);

                if (newStocksFilterList.Count != 0)
                {
                    lstStocks.SelectedIndex = -1;

                    lstStocks.Items.Clear();

                    for (int i = 0; i < newStocksFilterList.Count; i++)
                    {
                        lstStocks.Items.Add(newStocksFilterList[i]);
                    }


                }
                else
                {
                    MessageBox.Show("No items.");
                }


                lblTickerValue.Content = "Value";
                lblStockName.Content = "Stock";



                // newStocksFilterList.Clear();
            }
            catch
            {
                MessageBox.Show("Server unavailable. Please check the connection.");
            }

        }

        private void SetStartingFromDate(object sender, RoutedEventArgs e)
        {
            fromDate.DisplayDateStart = DateTime.ParseExact("01/01/2011", "dd/MM/yyyy", CultureInfo.InvariantCulture);

        }
    }
}
