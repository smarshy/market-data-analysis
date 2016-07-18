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
       
        private void ShowStockListWindow(object sender, RoutedEventArgs e)
        {
            StockListWindow newStockListWindow = new StockListWindow();
            newStockListWindow.Show();

        }

        private void ShowCompareStockWindow(object sender, RoutedEventArgs e)
        {
            CompareWindow newCompareWindow = new CompareWindow();
            newCompareWindow.Show();
        }

        private void ShowSpecificStockWindow(object sender, RoutedEventArgs e)
        {
            if (txtSearch.Text.Equals(null))
            {
                MessageBox.Show("Please enter a stock");
            }
            else
            {

            }
            
                SpecificStockWindow newSpecificStockWindow = new SpecificStockWindow();
                newSpecificStockWindow.Show();
            
            
        }

        private void PopulateList(object sender, RoutedEventArgs e)
        {
            string getURL = "http://192.168.239.51:8080/MarketDataAnalyserWeb/rest/stocks";
            WebClient newWebClient = new WebClient();


            Stream receivedStream = newWebClient.OpenRead(getURL);
            allStocks = (List<String>)serializerListString.ReadObject(receivedStream);
        }
    }
}
