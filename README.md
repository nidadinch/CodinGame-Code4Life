## CodinGame-Code4Life

It is a project for Akdeniz University Computer Science Department - Programming Languages Lesson. 
I started by writing the codes in java for understanding the game. My codes runs only 11 steps because i need to improve. 

Here is my strategy:

Oyunda daha fazla puan kazanabilmek için yapılması gereken 2 nokta var: 

### 1.Sample'ın efective seçilmesi
Oyuna ilk başladığımda, öncelikle sampleları ekrana yazdırdım. Sampleların özelliklerine baktığımda dikkatimi bazı samplelar'ın çok daha fazla molecule istemesi ve bu sebepten cost'un çok artması çekti. Cost'a oranla verdiği health point'lerin bazı samplelarda çok düşük olduğunu gördüm. Bu sebepten öncelikle bir algoritma yazmaya karar verdim. Bu algoritma effectiveCost'u hesaplıyor. Sample health / totalcost olarak elimde effectiveCost ile tüm samplelar arasından öncelikle yüksek puan veren ve costu düşük olanları almayı düşündüm. Çünkü 50 adet sample'ın içinden öncelikli olarak bunları alırsak, daha az cost ile daha çok puan kazanılabilir. 

### 2.Molecullerin efective kullanımı
Sonrasında moleculleri de ekrana yazdırdığımda şunu gördüm; her molekülden 99 adet var. Bu molekülleri olabildiğince efektif kullanmam gerektiğini farkettim. Çünkü sampleları effectiveCost ile aldığımızda, 10 adet sample'dan sonra örneğin, 

#### 50 A , 40 B , 60 C, 99 D, 99 E 

kaldığını varsayalım, eğer efective cost ile bu şekilde devam edersek sample almaya, sonunda elimizde şu gibi bir tablo kalabiliyor:

#### 0 A, 2 B, 10 C, 80 D, 96 E 

Eğer bu şekilde olursa kalan sampleların içinde eğer A molekülü isteyen bir sample varsa, hata alırız. 
Bunu çözmek için de günlük hayatta da ATM'lerde kullanılan bir algoritma yazılmalı. Bu algoritmaya göre, her zaman elimizdeki AvailbleA, Available B.... hepsinin oranına göre sample seçilmeli.


Eğer kodumu tamamlamayı yetiştirseydim stratejim şu şekilde olacaktı:
Robot tek seferde 3 sample, 10 molekül taşıybiliyor.Sample'lardan effectiveCost'u en yüksek olanlar çok az, bu yüzden ilk olarak onları alıp, işler. 3 sample, 10 molekül ve laboratuar. Ardından tekrar kalan sample'ları 3e tamamlar, gerekli molekülleri alır ve tekrar işler. Sonrasında effective cost'u 1'den küçük olan sample'larda elimizde kalan availableA, Available B... oranına göre sıradan alır. 


