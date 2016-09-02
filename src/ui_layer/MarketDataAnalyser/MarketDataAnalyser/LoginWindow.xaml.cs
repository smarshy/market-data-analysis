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
    /// Interaction logic for LoginWindow.xaml
    /// </summary>
    public partial class LoginWindow : Window
    {
        public LoginWindow()
        {
            InitializeComponent();
        }

        public static List<String> allStocks = new List<String>();
        public static List<String> allStocksLiffe = new List<string>();
        public static List<String> allStocksForex = new List<string>();
        public static Nasdaq defaultStock = new Nasdaq();

        DataContractJsonSerializer serializerListString = new DataContractJsonSerializer(typeof(List<String>));
        DataContractJsonSerializer serializerNasdaq = new DataContractJsonSerializer(typeof(Nasdaq));
        DataContractJsonSerializer serializerStockMarkets = new DataContractJsonSerializer(typeof(StockMarkets));


        private void ShowMainWindow(object sender, RoutedEventArgs e)
        {
            string password = passwordLogin.Password;
            if (!(String.IsNullOrWhiteSpace(txtUsernameLogin.Text) || String.IsNullOrWhiteSpace(password)))
            {
               
                MainWindow newMainWindow = new MainWindow();
                newMainWindow.Show();
                this.Close();
            }
            else
            {
                MessageBox.Show("Please enter all details");
            }
        }

        private void ShowSignUpWindow(object sender, RoutedEventArgs e)
        {
            SignUpWindow newSignUpWindow = new SignUpWindow();
            newSignUpWindow.Show();
            this.Close();
        }

        private void LoadLogo(object sender, StylusEventArgs e)
        {
            
            //BitmapImage newBitmapImage = new BitmapImage();
            //newBitmapImage.BeginInit();
            //newBitmapImage.UriSource = new Uri("C:\\Users\\Grad51\\Downloads\\Ayush_Goyal_VB\\ui_layer\\design\\LogoTeam20.png");
            //newBitmapImage.CacheOption = BitmapCacheOption.OnLoad;
            //newBitmapImage.EndInit();
            ////var image = sender as Image;
            //imgLogoLogin.Source = newBitmapImage;
        }

        private void LoadEverything(object sender, RoutedEventArgs e)
        {
            string getURL = "http://10.87.198.158:8080/MarketDataAnalyserWeb/rest/stocks";
            WebClient newWebClient = new WebClient();
            newWebClient.Proxy = null;

            try {
                Stream receivedStream = newWebClient.OpenRead(getURL);
                StockMarkets newStockMarkets = (StockMarkets)serializerStockMarkets.ReadObject(receivedStream);

                allStocks = newStockMarkets.nasdaqStockList;
                allStocksLiffe = newStockMarkets.liffeStockList;
                allStocksForex = newStockMarkets.forexStockList;
                defaultStock = newStockMarkets.defaultStock;

                //allStocks = (List<String>)serializerListString.ReadObject(receivedStream);
            }
            catch
            {
                MessageBox.Show("Server unavailable. Please check the connection.");
            }
        }

        public string PipelineTest()
        {
            string getURL = "";
            WebClient newWebClient = new WebClient();
            newWebClient.Proxy = null;

            string receivedString = newWebClient.DownloadString(getURL);

            return receivedString;

        }
    }
}
