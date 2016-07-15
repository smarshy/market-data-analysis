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
            
            
                SpecificStockWindow newSpecificStockWindow = new SpecificStockWindow();
                newSpecificStockWindow.Show();
            
            
        }
    }
}
