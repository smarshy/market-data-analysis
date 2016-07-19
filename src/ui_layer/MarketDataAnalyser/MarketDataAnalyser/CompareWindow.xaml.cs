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
using System.Windows.Shapes;
using System.IO;
using System.Runtime.Serialization;
using System.Runtime.Serialization.Json;
using System.Net;
using System.Windows.Controls.DataVisualization.Charting;

namespace MarketDataAnalyser
{
    /// <summary>
    /// Interaction logic for CompareWindow.xaml
    /// </summary>
    public partial class CompareWindow : Window
    {
        public CompareWindow()
        {
            InitializeComponent();
        }

        //public List<String> allStocks = new List<String>();
        //DataContractJsonSerializer serializerListString = new DataContractJsonSerializer(typeof(List<String>));
        //DataContractJsonSerializer serializerNasdaq = new DataContractJsonSerializer(typeof(Nasdaq));
        

        

        private void PopulateComboBoxes(object sender, RoutedEventArgs e)
        {
            MainWindow newMainWindow = new MainWindow();


            for (int i = 0; i < LoginWindow.allStocks.Count; i++)
            {
                listBoxFirst.Items.Add(LoginWindow.allStocks[i]);
            }

            for (int i = 0; i < LoginWindow.allStocks.Count; i++)
            {
                listBoxSecond.Items.Add(LoginWindow.allStocks[i]);
            }
        }

        private void CompareTheStocks(object sender, RoutedEventArgs e)
        {
            if(dateFrom.SelectedDate != null && dateTo.SelectedDate != null)
            {


                string getURL = "http://10.87.205.72:8080/MarketDataAnalyserWeb/rest/stocks";
                WebClient newWebClient = new WebClient();
            

                DateTime fromDate = (DateTime)dateFrom.SelectedDate;
                DateTime toDate = (DateTime)dateTo.SelectedDate;

                var newSerializer = new System.Web.Script.Serialization.JavaScriptSerializer();
                string receivedString = newWebClient.DownloadString(getURL + "/" + listBoxFirst.SelectedItem.ToString() 
                    + "/" + listBoxSecond.SelectedItem.ToString() + "/" + fromDate.ToString("yyyyMMdd")
                    +"/"+toDate.ToString("yyyyMMdd"));
                CompareStocks newCompareStocks = newSerializer.Deserialize<CompareStocks>(receivedString);


                Nasdaq newNasdaqFirst = newCompareStocks.stock1;
                Nasdaq newNasdaqSecond = newCompareStocks.stock2;
                List<Nasdaq> newNasdaqChartFirst = newCompareStocks.listStock1;
                List<Nasdaq> newNasdaqChartSecond = newCompareStocks.listStock2;

                lblStockNameFirst.Content = newNasdaqFirst.ticker;
                lblOpeningPriceFirst.Content = newNasdaqFirst.openingPrice;
                lblClosingPriceFirst.Content = newNasdaqFirst.closingPrice;
                lblHighFirst.Content = newNasdaqFirst.high;
                lblLowFirst.Content = newNasdaqFirst.low;
                lblVolumeFirst.Content = newNasdaqFirst.volume;

                if (newNasdaqFirst.upArrow)
                {
                    greenArrowFirst.Visibility = Visibility.Visible;
                }
                else
                {
                    redArrowFirst.Visibility = Visibility.Visible;
                }

                lblStockNameSecond.Content = newNasdaqSecond.ticker;
                lblOpeningPriceSecond.Content = newNasdaqSecond.openingPrice;
                lblClosingPriceSecond.Content = newNasdaqSecond.closingPrice;
                lblHighSecond.Content = newNasdaqSecond.high;
                lblLowSecond.Content = newNasdaqSecond.low;
                lblVolumeSecond.Content = newNasdaqSecond.volume;

                if (newNasdaqSecond.upArrow)
                {
                    greenArrowSecond.Visibility = Visibility.Visible;
                }
                else
                {
                    redArrowSecond.Visibility = Visibility.Visible;
                }


                List<KeyValuePair<int, decimal>> newKeyValuePairChartFirst = new List<KeyValuePair<int, decimal>>();

                for (int i = 0; i < newNasdaqChartFirst.Count; i++)
                {
                    newKeyValuePairChartFirst.Add(new KeyValuePair<int, decimal>(newNasdaqChartFirst[i].exchangeDate,
                        newNasdaqChartFirst[i].closingPrice));
                }


                LineSeries newLineSeriesFirst = new LineSeries();
                newLineSeriesFirst.Title = listBoxFirst.SelectedItem.ToString();
                newLineSeriesFirst.ItemsSource = newKeyValuePairChartFirst;
                newLineSeriesFirst.DependentValuePath = "Value";
                newLineSeriesFirst.IndependentValuePath = "Key";
                lineChart.Series.Clear();
                lineChart.Series.Add(newLineSeriesFirst);

                List<KeyValuePair<int, decimal>> newKeyValuePairChartSecond = new List<KeyValuePair<int, decimal>>();

                for (int i = 0; i < newNasdaqChartSecond.Count; i++)
                {
                    newKeyValuePairChartSecond.Add(new KeyValuePair<int, decimal>(newNasdaqChartSecond[i].exchangeDate,
                        newNasdaqChartSecond[i].closingPrice));
                }


                LineSeries newLineSeriesSecond = new LineSeries();
                newLineSeriesSecond.Title = listBoxSecond.SelectedItem.ToString();
                newLineSeriesSecond.ItemsSource = newKeyValuePairChartSecond;
                newLineSeriesSecond.DependentValuePath = "Value";
                newLineSeriesSecond.IndependentValuePath = "Key";
              
                lineChart.Series.Add(newLineSeriesSecond);



            }
            else
            {
                MessageBox.Show("Select dates");
            }
        }

        private void ShowExchangeFirst(object sender, RoutedEventArgs e)
        {
            comboBoxExchangeFirst.Items.Add("NASDAQ");
            comboBoxExchangeFirst.Items.Add("NYSE");
            comboBoxExchangeFirst.Items.Add("LIFFE");
        }

        private void ShowExchangeSecond(object sender, RoutedEventArgs e)
        {
            comboBoxExchangeSecond.Items.Add("NASDAQ");
            comboBoxExchangeSecond.Items.Add("NYSE");
            comboBoxExchangeSecond.Items.Add("LIFFE");
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
    }
}
