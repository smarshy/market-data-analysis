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
    /// Interaction logic for LoginWindow.xaml
    /// </summary>
    public partial class LoginWindow : Window
    {
        public LoginWindow()
        {
            InitializeComponent();
        }

        private void ShowMainWindow(object sender, RoutedEventArgs e)
        {
            if (txtUsernameLogin.Text != null)
            {
                MainWindow newMainWindow = new MainWindow();
                newMainWindow.Show();
            }
        }

        private void ShowSignUpWindow(object sender, RoutedEventArgs e)
        {
            SignUpWindow newSignUpWindow = new SignUpWindow();
            newSignUpWindow.Show();
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
            
        }
    }
}
