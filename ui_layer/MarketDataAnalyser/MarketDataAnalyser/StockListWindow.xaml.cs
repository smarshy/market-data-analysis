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

        private void ChangeSelection(object sender, SelectionChangedEventArgs e)
        {
            lblStockName.Content = lstStocks.SelectedItem;
        }

        private void ShowStockList(object sender, RoutedEventArgs e)
        {
            lstStocks.Items.Add("Apple");
            lstStocks.Items.Add("Microsoft");
            lstStocks.Items.Add("Oracle");
            lstStocks.Items.Add("Citi");
            lstStocks.Items.Add("Google");
        }

        private void ShowSortChartOptions(object sender, RoutedEventArgs e)
        {
            comboBoxSortChart.Items.Add("By week");
            comboBoxSortChart.Items.Add("By month");
            comboBoxSortChart.Items.Add("By year");
        }
    }
}
