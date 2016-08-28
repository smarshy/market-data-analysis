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
    /// Interaction logic for SpecificStockWindow.xaml
    /// </summary>
    public partial class SpecificStockWindow : Window
    {
        public SpecificStockWindow()
        {
            InitializeComponent();
        }

        private void ShowChartSortOptions(object sender, RoutedEventArgs e)
        {
            comboBoxSortChart.Items.Add("By week");
            comboBoxSortChart.Items.Add("By month");
            comboBoxSortChart.Items.Add("By year");
        }
    }
}
