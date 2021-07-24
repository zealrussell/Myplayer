/**
 * 
 */
$(window).on('scroll', function(e) {

    if ($(window).scrollTop() > 300) {
      $("header").addClass("smaller");
    } else {
      if ($("header").hasClass("smaller")) {
        $("header").removeClass("smaller");
      }
    }
    
  });
  
  $(window).on('scroll', function(e) {
   if ($(window).scrollTop() > 300) {
      $("#banner").addClass("smaller");
    } else {
      if ($("#banner").hasClass("smaller")) {
        $("#banner").removeClass("smaller");
      }
    }
  });