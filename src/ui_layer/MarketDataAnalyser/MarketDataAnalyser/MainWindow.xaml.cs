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
using Microsoft.Win32;
using System.IO;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Json;
using System.Net;
using System.Windows.Controls.DataVisualization.Charting;

namespace MarketDataAnalyser
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

        // public static List<String> allStocks = new List<String>();
        DataContractJsonSerializer serializerListString = new DataContractJsonSerializer(typeof(List<String>));
        DataContractJsonSerializer serializerNasdaq = new DataContractJsonSerializer(typeof(Nasdaq));
        public static List<Nasdaq> newNasdaqList = new List<Nasdaq>();


        private void ShowStockListWindow(object sender, RoutedEventArgs e)
        {
            StockListWindow newStockListWindow = new StockListWindow();
            newStockListWindow.Show();
            this.Close();

        }

        private void ShowCompareStockWindow(object sender, RoutedEventArgs e)
        {
            CompareWindow newCompareWindow = new CompareWindow();
            newCompareWindow.Show();
            this.Close();
        }

        private void ShowSpecificStockWindow(object sender, RoutedEventArgs e)
        {
            if (String.IsNullOrWhiteSpace(txtSearch.Text))
            {
                MessageBox.Show("Please enter a stock");
                txtSearch.Text = String.Empty;
            }
            else
            {



                try
                {

                    string getURL = "http://10.87.205.72:8080/MarketDataAnalyserWeb/rest/stocks/NASDAQ/details";
                    WebClient newWebClient = new WebClient();
                    newWebClient.Proxy = null;

                    greenArrow.Visibility = Visibility.Hidden;
                    redArrow.Visibility = Visibility.Hidden;

                    var newSerializer = new System.Web.Script.Serialization.JavaScriptSerializer();
                    string receivedString = newWebClient.DownloadString(getURL + "/" + txtSearch.Text.ToString());
                    Nasdaq newNasdaq = newSerializer.Deserialize<Nasdaq>(receivedString);



                    lblStockName.Content = newNasdaq.ticker;
                    lblOpeningPrice.Content = newNasdaq.openingPrice;
                    lblClosingPrice.Content = newNasdaq.closingPrice;
                    lblHigh.Content = newNasdaq.high;
                    lblLow.Content = newNasdaq.low;
                    lblVolume.Content = newNasdaq.volume;
                    lblTickerValue.Content = Math.Abs(newNasdaq.upArrow);

                    if (Decimal.Compare(newNasdaq.upArrow, 0) >= 0)
                    {
                        greenArrow.Visibility = Visibility.Visible;
                    }
                    else
                    {
                        redArrow.Visibility = Visibility.Visible;
                    }
                    //MessageBox.Show(newNasdaq.ToString());

                    string getURLChart = "http://10.87.205.72:8080/MarketDataAnalyserWeb/rest/stocks/NASDAQ/variation";

                    string receivedStringChart = newWebClient.DownloadString(getURLChart + "/" +
                        txtSearch.Text.ToString());
                    newNasdaqList = newSerializer.Deserialize<List<Nasdaq>>(receivedStringChart);
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
                catch
                {
                    MessageBox.Show("Stock does not exist");
                }
            }
        }


        private void ShowLoginWindow(object sender, RoutedEventArgs e)
        {
            LoginWindow newLoginWindow = new LoginWindow();
            newLoginWindow.Show();
            this.Close();
        }

        private void ShowDefaultStock(object sender, RoutedEventArgs e)
        {

            try
            {
                string getURL = "http://10.87.205.72:8080/MarketDataAnalyserWeb/rest/stocks/NASDAQ/details";
                WebClient newWebClient = new WebClient();
                newWebClient.Proxy = null;

                greenArrow.Visibility = Visibility.Hidden;
                redArrow.Visibility = Visibility.Hidden;


                var newSerializer = new System.Web.Script.Serialization.JavaScriptSerializer();
                string receivedString = newWebClient.DownloadString(getURL + "/AAPL");
                Nasdaq newNasdaq = newSerializer.Deserialize<Nasdaq>(receivedString);

                // Nasdaq newTempNasdaq = LoginWindow.defaultStock;

                lblStockName.Content = "CITI";
                lblOpeningPrice.Content = newNasdaq.openingPrice;
                lblClosingPrice.Content = newNasdaq.closingPrice;
                lblHigh.Content = newNasdaq.high;
                lblLow.Content = newNasdaq.low;
                lblVolume.Content = newNasdaq.volume;

                lblTickerValue.Content = Math.Abs(newNasdaq.upArrow);

                if (Decimal.Compare(newNasdaq.upArrow, 0) >= 0)
                {
                    greenArrow.Visibility = Visibility.Visible;
                }
                else
                {
                    redArrow.Visibility = Visibility.Visible;
                }
                //MessageBox.Show(newNasdaq.ToString());

                string getURLChart = "http://10.87.205.72:8080/MarketDataAnalyserWeb/rest/stocks/NASDAQ/variation";

                string receivedStringChart = newWebClient.DownloadString(getURLChart + "/" + newNasdaq.ticker.ToString());
                List<Nasdaq> newNasdaqListDefault = newSerializer.Deserialize<List<Nasdaq>>(receivedStringChart);
                List<KeyValuePair<int, decimal>> newKeyValuePairChart = new List<KeyValuePair<int, decimal>>();

                for (int i = 0; i < newNasdaqListDefault.Count; i++)
                {
                    newKeyValuePairChart.Add(new KeyValuePair<int, decimal>(newNasdaqListDefault[i].exchangeDate,
                        newNasdaqListDefault[i].closingPrice));
                }



                LineSeries newLineSeries = new LineSeries();
                newLineSeries.Title = "CITI";
                newLineSeries.ItemsSource = newKeyValuePairChart;
                newLineSeries.DependentValuePath = "Value";
                newLineSeries.IndependentValuePath = "Key";
                lineChart.Series.Clear();
                lineChart.Series.Add(newLineSeries);
                lineChart.Title = "Price Trend";



            }
            catch
            {
                MessageBox.Show("Server unavailable. Please check the connection.");
            }

        }

        private void ShowMovingAverage(object sender, RoutedEventArgs e)
        {
            if (String.IsNullOrWhiteSpace(txtSearch.Text))
            {
                MessageBox.Show("Please enter a stock.");
            }
            else
            {
                try
                {
                    string getURLChart = "http://10.87.205.72:8080/MarketDataAnalyserWeb/rest/stocks/movingAverage";
                    var newSerializer = new System.Web.Script.Serialization.JavaScriptSerializer();




                    WebClient newWebClient = new WebClient();

                    string receivedStringChart = newWebClient.DownloadString(getURLChart + "/" + lblStockName.Content);


                    List<MovingAverageTrend> newMovAvgTrendChart = newSerializer.Deserialize<List<MovingAverageTrend>>(receivedStringChart);
                    List<KeyValuePair<int, decimal>> newKeyValuePairChart = new List<KeyValuePair<int, decimal>>();
                    List<KeyValuePair<int, decimal>> newKeyValuePairChartPrice = new List<KeyValuePair<int, decimal>>();


                    for (int i = 0; i < newMovAvgTrendChart.Count; i++)
                    {
                        newKeyValuePairChart.Add(new KeyValuePair<int, decimal>(newMovAvgTrendChart[i].date,
                            newMovAvgTrendChart[i].ma));
                    }

                    for (int i = 0; i < newNasdaqList.Count; i++)
                    {
                        newKeyValuePairChartPrice.Add(new KeyValuePair<int, decimal>(newNasdaqList[i].exchangeDate,
                            newNasdaqList[i].closingPrice));
                    }

                    LineSeries newLineSeries = new LineSeries();
                    newLineSeries.Title = "Moving average";
                    newLineSeries.ItemsSource = newKeyValuePairChart;
                    newLineSeries.DependentValuePath = "Value";
                    newLineSeries.IndependentValuePath = "Key";

                    LineSeries newLineSeriesPrice = new LineSeries();
                    newLineSeriesPrice.Title = "Price";
                    newLineSeriesPrice.ItemsSource = newKeyValuePairChartPrice;
                    newLineSeriesPrice.DependentValuePath = "Value";
                    newLineSeriesPrice.IndependentValuePath = "Key";

                    lineChart.Series.Clear();
                    // lineChart.Series.Add(newLineSeriesPrice);
                    lineChart.Series.Add(newLineSeries);
                    lineChart.Title = " Moving Average Trend";

                }
                catch
                {
                    MessageBox.Show("Server unavailable. Please check the connection.");
                }
            }
        }

        private void ShowVolumePriceTrend(object sender, RoutedEventArgs e)
        {
            if (String.IsNullOrWhiteSpace(txtSearch.Text))
            {
                MessageBox.Show("Please enter a stock.");
            }
            else
            {

                try
                {
                    string getURLChart = "http://10.87.205.72:8080/MarketDataAnalyserWeb/rest/stocks/volumePriceTrend";
                    var newSerializer = new System.Web.Script.Serialization.JavaScriptSerializer();




                    WebClient newWebClient = new WebClient();

                    string receivedStringChart = newWebClient.DownloadString(getURLChart + "/" + lblStockName.Content);


                    List<VolumePriceTrend> newVolProceTrendChart = newSerializer.Deserialize<List<VolumePriceTrend>>(receivedStringChart);
                    List<KeyValuePair<int, decimal>> newKeyValuePairChart = new List<KeyValuePair<int, decimal>>();
                    List<KeyValuePair<int, decimal>> newKeyValuePairChartPrice = new List<KeyValuePair<int, decimal>>();



                    for (int i = 0; i < newVolProceTrendChart.Count; i++)
                    {
                        newKeyValuePairChart.Add(new KeyValuePair<int, decimal>(newVolProceTrendChart[i].date,
                            newVolProceTrendChart[i].vpt));
                    }

                    for (int i = 0; i < newNasdaqList.Count; i++)
                    {
                        newKeyValuePairChartPrice.Add(new KeyValuePair<int, decimal>(newNasdaqList[i].exchangeDate,
                            newNasdaqList[i].closingPrice));
                    }

                    LineSeries newLineSeries = new LineSeries();
                    newLineSeries.Title = lblStockName.Content;
                    newLineSeries.ItemsSource = newKeyValuePairChart;
                    newLineSeries.DependentValuePath = "Value";
                    newLineSeries.IndependentValuePath = "Key";

                    LineSeries newLineSeriesPrice = new LineSeries();
                    newLineSeriesPrice.Title = "Price";
                    newLineSeriesPrice.ItemsSource = newKeyValuePairChartPrice;
                    newLineSeriesPrice.DependentValuePath = "Value";
                    newLineSeriesPrice.IndependentValuePath = "Key";

                    lineChart.Series.Clear();
                    // lineChart.Series.Add(newLineSeriesPrice);
                    lineChart.Series.Add(newLineSeries);
                    lineChart.Title = "Volume Price Trend";
                }
                catch
                {
                    MessageBox.Show("Server unavailable. Please check the connection.");
                }
            }

        }

        private void ShowPriceTrend(object sender, RoutedEventArgs e)
        {
            List<KeyValuePair<int, decimal>> newKeyValuePairChartPrice = new List<KeyValuePair<int, decimal>>();
            for (int i = 0; i < newNasdaqList.Count; i++)
            {
                newKeyValuePairChartPrice.Add(new KeyValuePair<int, decimal>(newNasdaqList[i].exchangeDate,
                    newNasdaqList[i].closingPrice));
            }

            LineSeries newLineSeriesPrice = new LineSeries();
            newLineSeriesPrice.Title = "Price";
            newLineSeriesPrice.ItemsSource = newKeyValuePairChartPrice;
            newLineSeriesPrice.DependentValuePath = "Value";
            newLineSeriesPrice.IndependentValuePath = "Key";
            lineChart.Series.Clear();
            lineChart.Series.Add(newLineSeriesPrice);

        }
    }
}
