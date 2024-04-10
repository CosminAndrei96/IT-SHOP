var products_list = []

var maximum_number_of_products = 40;

for (let i = 0; i < maximum_number_of_products; i++) {


    var product_div =  document.createElement("div")
    product_div.className = "product"

    var name_div =  document.createElement("div")
    name_div.className = "name"

    var price_div =  document.createElement("div")
    price_div.className = "price"

    var buy_div =  document.createElement("div")
    buy_div.className = "buy"

    const newButton = document.createElement('button');
    newButton.textContent = 'Buy';

    product_div.appendChild(name_div)

    product_div.appendChild(price_div)

    product_div.appendChild(buy_div)

    buy_div.appendChild(newButton)

    products_list.push(product_div)

}

view_formation = [3, 3, 3, 3]

var view_div = document.getElementsByClassName("view")

function create_view(formation, index_view){

    for (let i = 0; i < formation.length; i++) {
        const number_of_products = formation[i];

        var line_div =  document.createElement("div")
        line_div.className = "line"

        for (let j = 0; j < number_of_products; j++) {
          var new_product = products_list.pop()
           line_div.appendChild(new_product)
        }

        view_div[index_view].appendChild(line_div)

    }
}

create_view(view_formation, 0)

var view_list = ["Sistem Desktop Gaming ACER Predator Orion 3000, Intel Core i7-13700F pana la 5.2GHz, 16GB, SSD 512GB, NVIDIA GeForce RTX 3070 8GB, Free DOS",
"Sistem PC All in One MYRIA MY8319, Intel Core i5-1135G7 pana la 4.2GHz, 23.8 Full HD, 16GB, SSD 512GB, Intel Iris Xe Graphics, Ubuntu",
"Sistem Desktop PC APPLE Mac Studio mqh63ze/a, Apple M2 Ultra, 64GB, SSD 1TB, Grafica integrata, macOS Ventura - INT",
"Incarcator retea BELKIN WCH010vfBK, 2xUSB-C 65W, 2xUSB-A 12W, Power Delivery (PD) 108W, negru",
"Sistem Desktop Gaming LENOVO Legion T5 26IRB8, Intel Core i5-13400 pana la 4.6GHz, 16GB, SSD 1TB, NVIDIA GeForce RTX 3060 12GB, Free Dos",
"Sistem All in One LENOVO Yoga AIO 7 27ARH7, AMD Ryzen 7 6800H pana la 4.7GHz, 27 UHD, 32GB, SSD 1TB, AMD Radeon RX 6600M 8GB, Windows 11 Home, Cloud Grey",
"Monitor Gaming curbat LED VA AOC C24G2AE/BK, 23.6, Full HD, 165Hz, FreeSync Premium, negru",
"Monitor Gaming IPS LED DELL S2421HN, 23.8 Full HD, FreeSync, 75Hz, gri",
"Monitor Gaming IPS LED DELL S2721HN, 27 Full HD, FreeSync, 75Hz, gri",
"Multifunctional laser monocrom XEROX WorkCentre 3025BI, A4, USB, Wi-Fi",
"Multifunctional inkjet color HP DeskJet 2720e All-in-One, A4, USB, Wi-Fi, HP+ Eligibil",
"Switch TP-LINK LS1005G, 5 porturi Gigabit, negru"]

var view_list_imgs = [
    "https://lcdn.altex.ro/media/catalog/product/p/r/predator_orion_3000_po3_650_light_blue_glass_1_eee2c08e.jpg",
    "https://lcdn.altex.ro/media/catalog/product/s/i/sistem-pc-all-in-one-myria_1_12bee99b.jpg",
    "https://lcdn.altex.ro/media/catalog/product/a/p/apple_Mac_Studio_silver_2022_3.jpg",
    "https://lcdn.altex.ro/media/catalog/product/a/i/aiswch010vfbk_3__f69d96d8.jpg",
    "https://lcdn.altex.ro/media/catalog/product/l/e/legion_t5_26irb8_2_47332471.jpg",
    "https://lcdn.altex.ro/media/catalog/product/y/o/yoga-aio-7-27_1_03be8ee6.jpg",
    "https://lcdn.altex.ro/media/catalog/product/a/o/aoc_c24g2aebk_1_d9f99b80.jpg",
    "https://lcdn.altex.ro/media/catalog/product/s/2/s2421hn_cfp_00000ff090_gy-1280x1280.jpg",
    "https://lcdn.altex.ro/media/catalog/product/s/2/s2421hn_cfp_00000ff090_gy-1280x1280.jpg",
    "https://lcdn.altex.ro/media/catalog/product/3/0/3025v_bi_1_2.jpg",
    "https://lcdn.altex.ro/media/catalog/product/2/6/26K67B_7.jpg",
    "https://lcdn.altex.ro/media/catalog/product/S/W/SWHLS1005G_1.jpg"
]

var view_list_prices = [
    "5000",
    "500",
    "1000",
    "600",
    "2000",
    "800",
    "1500",
    "7000",
    "700",
    "730",
    "540",
    "150"
]

var products_div = document.getElementsByClassName("name")
var products_imgs = document.getElementsByClassName("product")
var products_prices = document.getElementsByClassName("price")
var products_buy = document.getElementsByClassName("buy")

for (let i = 0; i < maximum_number_of_products; i++) {
    if (i < maximum_number_of_products){
        const product_div = products_div[i];
        product_div.innerHTML = view_list[i]

        const product_imgs =  products_imgs[i]
        console.log(product_imgs)
        product_imgs.style.backgroundImage = "url("+ view_list_imgs[i] +")"

        const product_prices = products_prices[i];
        product_prices.innerHTML = view_list_prices[i]

    }

}


function rabat(){
    var eveniment_div = document.createElement("div");
    eveniment_div.className= "discount";

    var galben_div = document.createElement("div");
    galben_div.className= "banner";

    eveniment_div.appendChild(galben_div);

    var products_divs = document.getElementsByClassName("product");

    for (let i = 0; i < products_divs.length; i++) {
        const element = products_divs[i];
        const eveniment_div_clone = eveniment_div.cloneNode(true)
        element.appendChild(eveniment_div_clone)

    }
}

rabat();




