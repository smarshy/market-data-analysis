﻿using System;
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

        public static List<String> allStocks = new List<String>();
        DataContractJsonSerializer serializerListString = new DataContractJsonSerializer(typeof(List<String>));
        DataContractJsonSerializer serializerNasdaq = new DataContractJsonSerializer(typeof(Nasdaq));
        


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
            //if (txtSearch.SelectionLength == 0)
            //{
            //    MessageBox.Show("Please enter a stock");
            //}
            //else
            //{

            //}

            string getURL = "http://10.87.205.72:8080/MarketDataAnalyserWeb/rest/stocks/query";
            WebClient newWebClient = new WebClient();
   

            var newSerializer = new System.Web.Script.Serialization.JavaScriptSerializer();
            string receivedString = newWebClient.DownloadString(getURL + "?ticker=" + txtSearch.Text.ToString());
            Nasdaq newNasdaq = newSerializer.Deserialize<Nasdaq>(receivedString);


            lblStockName.Content = newNasdaq.ticker;
            lblOpeningPrice.Content = newNasdaq.openingPrice;
            lblClosingPrice.Content = newNasdaq.closingPrice;
            lblHigh.Content = newNasdaq.high;
            lblLow.Content = newNasdaq.low;
            lblVolume.Content = newNasdaq.volume;
            //MessageBox.Show(newNasdaq.ToString());

            string getURLChart = "http://10.87.205.72:8080/MarketDataAnalyserWeb/rest/stocks/variation";

            string receivedStringChart = newWebClient.DownloadString(getURLChart + "/" + 
                txtSearch.Text.ToString() + "/20110103/20110113");
            List<Nasdaq> newNasdaqList = newSerializer.Deserialize<List<Nasdaq>>(receivedStringChart);
            List<KeyValuePair<int,decimal>> newKeyValuePairChart = new List<KeyValuePair<int, decimal>>();
            
            for(int i = 0; i < newNasdaqList.Count; i++)
            {
               newKeyValuePairChart.Add( new KeyValuePair<int, decimal>(newNasdaqList[i].exchangeDate, 
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

        private void PopulateList(object sender, RoutedEventArgs e)
        {
            string getURL = "http://10.87.205.72:8080/MarketDataAnalyserWeb/rest/stocks";
            WebClient newWebClient = new WebClient();


            Stream receivedStream = newWebClient.OpenRead(getURL);
            allStocks = (List<String>)serializerListString.ReadObject(receivedStream);
        }

        private void ShowLoginWindow(object sender, RoutedEventArgs e)
        {
            LoginWindow newLoginWindow = new LoginWindow();
            newLoginWindow.Show();
            this.Close();
        }
    }
}
