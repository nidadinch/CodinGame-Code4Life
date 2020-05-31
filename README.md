## CodinGame-Code4Life
### Nida Dinç - 20160808047

It is a project for Akdeniz University Computer Science Department - Programming Languages Lesson. 
I started by writing the codes in java for understanding the game.Also you can find my Java codes in this repository.

Here is my strategy:


Oyunda daha fazla puan kazanabilmek için yapılması gereken 2 nokta var: 
### 1.Sample'ın effective seçilmesi
Oyuna ilk başladığımda, öncelikle sampleları ekrana yazdırdım. Sampleların özelliklerine baktığımda  bazı sampleların çok daha fazla moleküle istemesi ve bu sebepten costun çok artması dikkatimi çekti. Cost'a oranla verdiği health point'lerin bazı samplelarda çok düşük olduğunu gördüm. Bu sebepten öncelikle bir algoritma yazmaya karar verdim. Bu algoritma effectiveCost'u hesaplıyor. Sample'ın health / totalcost oranını alıyor, elimde effectiveCost ile tüm samplelar arasından öncelikle yüksek puan veren ve costu düşük olanları almayı düşündüm. Çünkü 50 adet sample'ın içinden öncelikli olarak bunları alırsak, daha az cost ile daha çok puan kazanılabilir. 

### 2.Moleküllerin effective kullanımı
Sonrasında molekülleri de ekrana yazdırdığımda şunu gördüm; her molekülden 99 adet var. Bu molekülleri olabildiğince efektif kullanmam gerektiğini farkettim. Çünkü sampleları effectiveCost ile aldığımızda, 10 adet sample'dan sonra örneğin, 

#### 50 A , 40 B , 60 C, 99 D, 99 E 

kaldığını varsayalım, eğer effectiveCost ile bu şekilde devam edersek sample almaya, sonunda elimizde şu gibi bir tablo kalabiliyor:

#### 0 A, 2 B, 10 C, 80 D, 96 E 

Eğer bu şekilde olursa kalan sampleların içinde eğer A molekülü isteyen bir sample varsa, o zaman sample'ı labda üretemeyiz. 
Bunu çözmek için de günlük hayatta da ATM'lerde kullanılan bir algoritma aklıma geldi.Örneğin, ATM'ye gittiğimizde 500%L çekmek istersek hepsini 100TL olarak vermez. 3 x100 TL, 2x50 TL, 2x 20TL 1x10TL gibi bu parayı bölerek verir. Bunun sebebi de kalan nakit parayı efektif kullanabilmektir. Bu algoritmayı kendi kodumuza uyarlarsak, her zaman elimizdeki AvailbleA, Available B.... hepsinin oranına göre sample seçilmeli.


Robot tek seferde 3 sample, 10 molekül taşıyabiliyor.Sample'lardan effectiveCost'u en yüksek olanların sayısı zaten az, bu yüzden ilk olarak onları alıp, robot labda ilk olarak onları işler. (3 sample, 10 molekül ardından laboratuar). Ardından tekrar kalan sample'ları 3e tamamlar, gerekli molekülleri alır ve tekrar işler. Sonrasında effectiveCost'u 1'den küçük olan sample'larda elimizde kalan availableA, Available B... oranına göre sıradan alır. 


---------------------------------------------------------------------------------------------------------------------------------------
There are 2 points to be done to gain more points in the game:

### 1. Choosing effective samples
When I started the game, first i have printed the samples on the screen. When I looked at the properties of the samples, I caught my attention that some samples wanted more molecules and therefore the cost increased too much. I have seen that the health points it gives compared to the cost are very low in some samples. For this reason, I first decided to write an algorithm. This algorithm calculates effectiveCost. It takes the sample's health / totalcost ratio, and with the effectiveCost, I thought I would get the first high score and low cost among all samples. Because if we take these primarily from 50 samples, more points can be earned with less cost.


### 2. Effective use of molecules
Then when I printed the molecules on the screen, I saw this; there are 99 of each molecule. I realized that I should use these molecules as effectively as possible. Because when we take samples with effectiveCost, after 10 samples, for example,

#### 50 A, 40 B, 60 C, 99 D, 99 E

Let's assume that if we continue this way with effectiveCost, we can get a table like this:

#### 0 A, 2 B, 10 C, 80 D, 96 E

If this happens, if there is a sample in the remaining samples that requires A molecule, then we cannot produce the sample in the lab.
To solve this, an algorithm used in ATMs in our daily life came to my mind. For example, if we want to draw 500TL when we go to the ATM, it doesn't give them all as 100TL. 3 x 100 TL, 2x50 TL, 2x 20TL 1x10TL by dividing this money. The reason for this is to be able to use the remaining cash effectively. If we adapt this algorithm to our own code, we should always choose AvailbleA, Available B .... sample according to their ratio.


The robot can carry 3 samples, 10 molecules at a time. The number of samples with the highest effectiveCost is already very little, so take them first and process them first in the robot lab. (3 samples, 10 molecules followed by laboratory). Then it completes the remaining samples to 3, takes the necessary molecules and processes them again. Then, the availableA, which we have in the samples that their effectiveCost are less than 1, takes the effectiveCost in the ordinary according to the Available B ... ratio.
