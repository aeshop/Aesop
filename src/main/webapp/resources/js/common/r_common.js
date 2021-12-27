
    var swiperHead = new Swiper(".swiper_header", {
      spaceBetween: 100,
      centeredSlides: true,
      autoplay: {
        delay: 3300,
        disableOnInteraction: false
      },
      pagination: {
        el: ".swiper-pagination",
        clickable: true
      },
      navigation: {
        nextEl: ".swiper-button-next",
        prevEl: ".swiper-button-prev"
      }
    });

  /*<%-- footer JS --%>*/
 
    var swiperFooter = new Swiper(".swiper_footer", {
      direction: "vertical",
      slidesPerView: "auto",
      freeMode: true,
      scrollbar: {
        el: ".swiper-scrollbar",
      },
      mousewheel: true,
    });

    /* copyright */
    document.getElementById("copyright_btn_open").addEventListener("click", function () {
      const copyright = document.getElementById("copyright");

      copyright.style.display = "block";
      this.style.display = "none";

      document.getElementById("copyright_btn_close").addEventListener("click", function () {
        const copyrightBtn = document.getElementById("copyright_btn_open");

        copyright.style.display = "none";
        copyrightBtn.style.display = "block";
      });
    });

    $(".sidebar_categoty").children().hide();
    $(".sidebar_categoty").on("mouseover", function () {
      $(".sidebar_categoty").children().hide();
      $(this).children().show();
    });

    setTimeout(function () {
      $(".sidebar_categoty").on("mouseout", function () {

        $(this).children().hide();
      });

    });

   