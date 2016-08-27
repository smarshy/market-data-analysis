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
    /// Interaction logic for CompareWindow.xaml
    /// </summary>
    public partial class CompareWindow : Window
    {
        public CompareWindow()
        {
            InitializeComponent();
        }

        private void DropDownListFirst(object sender, RoutedEventArgs e)
        {
            comboBoxFirstStock.Items.Add("Apple");
            comboBoxFirstStock.Items.Add("Microsoft");
            comboBoxFirstStock.Items.Add("Oracle");
            comboBoxFirstStock.Items.Add("Citi");
            comboBoxFirstStock.Items.Add("Google");
        }

        private void DropDownListSecond(object sender, RoutedEventArgs e)
        {
            comboBoxSecondStock.Items.Add("Apple");
            comboBoxSecondStock.Items.Add("Microsoft");
            comboBoxSecondStock.Items.Add("Oracle");
            comboBoxSecondStock.Items.Add("Citi");
            comboBoxSecondStock.Items.Add("Google");
        }

        private void SelectionChangedFirst(object sender, SelectionChangedEventArgs e)
        {
            lblStockNameFirst.Content = comboBoxFirstStock.SelectedItem;
        }

        private void SelectionChangedSecond(object sender, SelectionChangedEventArgs e)
        {
            lblStockNameSecond.Content = comboBoxSecondStock.SelectedValue; 
        }
    }
}
