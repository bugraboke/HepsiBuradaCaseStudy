# HepsiBurada Case Study
- Bu çalışmada Hepsiburada'da ürün seçiminden sepet ekranına kadar uçtan uca bir senaryo oluşturulmuştur.
# Projeyi çalıştırmak
- HepsiburadaTest.java dosyasındaki hepsiburadatest() methodu çalıştırılır. 
#  Technologies used
- Java
- Selenium WebDriver
- Maven
- TestNG
# Varsayımlar
- Öncelikle projeyi daha kolay okuyup anlayabilmek, daha sonra da projede kolayca değişiklik yapabilmek için Page Object Model oluşturulur.
  # 1. Page Object Model(POM)
- Burada 3 tane başlık var. Bunlar: SearchPage, ProductPage ve CartPage.
- **SearchPage**: Kullanım amacı önce arama alanına basmak, aratmak istenilen ürünün yazılması, çıkan sonuçların doğrulanması, filtre uygulanması, daha sonra ise filtrenin uygulandığının doğrulanmasını sağlamaktır.
- **ProductPage**: Kullanım amacı istenilen ürünün seçilmesi, ürün sayfasına gidilmesi, doğru ürünün seçildiğinin doğrulanması, ürünün sepete eklenmesi ve sepet ekranına gidilmesini sağlamaktır.
- **CartPage**: Kullanım amacı ise seçtiğimiz ürünün sepette olduğunu doğrulanmasını sağlamaktır.
  # 2. Explicit Wait
- Öncelikle sitenin dinamik yapısından dolayı hem sayfanın her yeni sayfaya geçildiğinde yenileneceği, hem de sayfanın her zaman aynı sürede yüklenmeyeceği varsayılmıştır. Bunun için **Explicit wait(WebDriverwait)** kullanılmıştır.
  # 3. Ürün Doğrulama
- Seçilen ürünün detay sayfasına gidildiğini doğrularken URL'de ürün isminin hem küçük harflerle hem de her kelime arasına - işareti konulacağı varsayılmıştır. Bundan ötürü doğrulama işlemi için **verifyProductUrl** methodu uygulanmıştır. Bu method seçilen ürünün ismini önce küçük harflere çevirir, daha sonra aralarındaki boşlukları tireye çevirir. Daha sonra URL'nin içinde ürünün ismi geçiyorsa doğrulama başarılıdır.
  # 4. JavascriptExecutor
- Hepsiburada'nın yapısı bazı elementleri click() methodu ile tıklamayı engelleyeceği varsayılmıştır, bundan için tıklama yapılması gereken önemli alanlar için click() yerine **JavascriptExecutor** kullanılarak olabilecek tıklama sorunu giderilmiştir.
- Bazı elementlerin sayfanın aşağısında kalacağı, bundan dolayı elementin bulunamayacağı ve testin fail alacağı varsayılmıştır. Bunu önleyebilmek için JavascriptExecutor kullanılmıştır ve **scrollIntoView(true)** komutu kullanılmıştır.
  # 5. Çoklu Sekme
- Hepsiburada'da bir ürün seçildiğinde sitenin yeni bir sekmeye yönlendireceği varsayılmıştır. Bundan ötürü sonraki adımlarda elementlerin bulunamayıp fail alabileceği varsayılmıştır. Bunu önleyebilmek için **WindowHandle** fonksiyonu kullanılmıştır.
