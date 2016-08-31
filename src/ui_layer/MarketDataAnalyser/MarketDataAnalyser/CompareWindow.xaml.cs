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


            for (int i = 0; i < MainWindow.allStocks.Count; i++)
            {
                listBoxFirst.Items.Add(MainWindow.allStocks[i]);
            }

            for (int i = 0; i < MainWindow.allStocks.Count; i++)
            {
                listBoxSecond.Items.Add(MainWindow.allStocks[i]);
            }
        }

        private void CompareTheStocks(object sender, RoutedEventArgs e)
        {
            if(dateFrom.SelectedDate != null && dateTo.SelectedDate != null)
            {
                if (listBoxFirst.SelectedItem.Equals(listBoxSecond.SelectedItem))
                {
                    MessageBox.Show("Select different stocks");
                }
                else
                {
                    DateTime fromDate = (DateTime)dateFrom.SelectedDate;
                    DateTime toDate = (DateTime)dateTo.SelectedDate;

                    String[] sendingArray = {listBoxFirst.SelectedItem.ToString(),listBoxSecond.SelectedItem.ToString(),
                        fromDate.ToString("yyyyMMdd"),toDate.ToString("yyyyMMdd")};

                    DataContractJsonSerializer serializerStringArray = new DataContractJsonSerializer(typeof(String[]));
                    string getURL = "";

                    WebClient newWebClient = new WebClient();

                    Stream receivedStream = newWebClient.OpenRead(getURL + "?ticker1=\"" + listBoxFirst.SelectedItem.ToString()
                        +"\"&ticker2=\"" + listBoxSecond.SelectedItem.ToString() + "\"&fromDate=\"" +
                        fromDate.ToString("yyyyMMdd") + "\"&toDate\"" + toDate.ToString("yyyyMMdd")); 

                    




                }
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
