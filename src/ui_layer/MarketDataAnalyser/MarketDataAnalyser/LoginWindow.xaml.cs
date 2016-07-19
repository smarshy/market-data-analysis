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
        DataContractJsonSerializer serializerListString = new DataContractJsonSerializer(typeof(List<String>));
        DataContractJsonSerializer serializerNasdaq = new DataContractJsonSerializer(typeof(Nasdaq));


        private void ShowMainWindow(object sender, RoutedEventArgs e)
        {
            if (txtUsernameLogin.Text != null)
            {
                MainWindow newMainWindow = new MainWindow();
                newMainWindow.Show();
                this.Close();
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
            string getURL = "http://10.87.205.72:8080/MarketDataAnalyserWeb/rest/stocks";
            WebClient newWebClient = new WebClient();


            try {
                Stream receivedStream = newWebClient.OpenRead(getURL);
                allStocks = (List<String>)serializerListString.ReadObject(receivedStream);
            }
            catch
            {
                MessageBox.Show("Server unavailable");
            }
        }
    }
}
