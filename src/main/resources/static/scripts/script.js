// alert('Acesta este un site de test!');

// Dark mode version of the site

var state=0;
var main=document.getElementById('bodyMain');
var button=document.getElementById('darkMode');
var header=document.getElementById('main-nav');
var brand=document.getElementById("brand");
var dropdown_contentAccount=document.getElementById("dropdown-contentAccount")
var avatar = document.getElementById('avatar')
var promotions=document.getElementById("promotions")
var hero=document.getElementById("hero")
var content=document.getElementById("content")
var footer=document.getElementById("footer")


function lightdark(){
    if(state == 0){
        main.setAttribute('class', 'black')
        button.setAttribute('value','Light Mode')
        header.setAttribute('class','main-navBlack')
        // brand.classList.add("white")
        dropdown_contentAccount.setAttribute('class','orange')
        avatar.setAttribute('src','webSitePhotos/account-icon.png')
        promotions.classList.add("black")
        hero.classList.add("black")
        content.classList.add("black")
        footer.setAttribute('class', 'black')
        state=1;
    }else{
        main.setAttribute('class', 'white')
        button.setAttribute('value','Dark Mode')
        header.setAttribute('class','main-navWhite')
        // brand.classList.remove("white")
        dropdown_contentAccount.setAttribute('class','white')
        avatar.setAttribute('src','webSitePhotos/account-icon2.jpg')
        promotions.classList.remove("black")
        hero.classList.remove("black")
        content.classList.remove("black")
        footer.setAttribute('class', 'footer')
        state=0;
    }

}
button.addEventListener('click',lightdark)

var hero_div = document.getElementById("hero")
var hero_h1 = hero_div.getElementsByTagName("h1")[0]
var animated_text = hero_h1.innerHTML

const MAX_LENGTH = animated_text.length
var step=2;

function showtext(){
    hero_h1.innerHTML = animated_text.slice(0,step)
    step++
    if(step == MAX_LENGTH+1){
        step=2;
    }
}

setInterval(showtext, 150)

// var redBox = document.getElementById('redBox');
// function lightbox(){
//     redBox.setAttribute('style', 'display:table');
// }
// setTimeout(lightbox,5000);

// function lightboxClose(){
//     redBox.setAttribute('style', 'display:none');
// }

var imageArray=['dynamicPromotionsImages/image1.jpg',
                'dynamicPromotionsImages/image2.jpg',
                'dynamicPromotionsImages/image3.jpg',
                'dynamicPromotionsImages/image4.jpg',
                'dynamicPromotionsImages/image5.jpg',
                'dynamicPromotionsImages/image6.jpg',
                'dynamicPromotionsImages/image7.jpg',
                'dynamicPromotionsImages/image8.jpg',
                'dynamicPromotionsImages/image9.jpg',
                'dynamicPromotionsImages/image10.jpg',
                'dynamicPromotionsImages/image11.jpg',
                'dynamicPromotionsImages/image12.jpg',
                'dynamicPromotionsImages/image13.jpg',
                'dynamicPromotionsImages/image14.jpg',
                'dynamicPromotionsImages/image15.jpg',
                'dynamicPromotionsImages/image16.jpg',
                'dynamicPromotionsImages/image17.jpg',
                'dynamicPromotionsImages/image18.jpg',
                'dynamicPromotionsImages/image19.jpg',
                'dynamicPromotionsImages/image20.jpg',
                'dynamicPromotionsImages/image21.jpg',
                'dynamicPromotionsImages/image22.jpg'];
var imageIndex = 0;
var myImage = document.getElementsByClassName('picture');

// Image change at a given time
function changeImage(){ 
    // myImage.setAttribute('background-image: url()', imageArray[imageIndex]);
    document.getElementsByClassName("picture")[0].style.backgroundImage =  'url(' + imageArray[imageIndex]+')';
    imageIndex++;
    if(imageIndex>=imageArray.length){
        imageIndex=0;
    }
}
    setInterval(changeImage,3000);



    function next(){
    document.getElementsByClassName("picture")[0].style.backgroundImage =  'url(' + imageArray[imageIndex]+')';
    imageIndex++;
    if(imageIndex>=imageArray.length){
        imageIndex=0;
    }
}


function back(){
    document.getElementsByClassName("picture")[0].style.backgroundImage =  'url(' + imageArray[imageIndex]+')';
    imageIndex--;
    if(imageIndex=0){
        imageIndex=imageArray.length-1;
    }
}

