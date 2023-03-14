package com.example.online_shop.entity

import com.example.online_shop.data.api.FlashDTO
import com.example.online_shop.data.api.LatestDTO
import com.example.online_shop.data.api.ProductDTO
import com.example.online_shop.data.api.ProductDiscountDTO

object Plug {
    fun person() = Person(
        firstName = "Jon",
        lastName = "Hamilton",
        email = "hamilton@gmail.com",
        password = "1"
    )
    fun latest() = LatestDTO(
        listOf(
            ProductDTO(
                category = "Phones",
                name = "Samsung S10",
                price=1000,
                image_url= "https://www.dhresource.com/0x0/f2/albu/g8/M01/9D/19/rBVaV1079WeAEW-AAARn9m_Dmh0487.jpg"
            ),
            ProductDTO(
                category = "Games",
                name= "Sony Playstation 5",
                price = 700,
                image_url = "https://avatars.mds.yandex.net/get-mpic/6251774/img_id4273297770790914968.jpeg/orig"
            ),
            ProductDTO(
                category ="Games",
                name= "Xbox ONE",
                price=500,
                image_url="https://www.tradeinn.com/f/13754/137546834/microsoft-xbox-xbox-one-s-1tb-console-additional-controller.jpg"
            ),
            ProductDTO(
                category ="Cars",
                name= "BMW X6M",
                price= 35000,
                image_url= "https://mirbmw.ru/wp-content/uploads/2022/01/manhart-mhx6-700-01.jpg"
            ),
            ProductDTO(
                category= "Kids",
                name= "Reebok Classic",
                price= 24,
                image_url= "https://assets.reebok.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/3613ebaf6ed24a609818ac63000250a3_9366/Classic_Leather_Shoes_-_Toddler_White_FZ2093_01_standard.jpg"
            ),
            ProductDTO(
                category= "Kids",
                name= "New Balance Sneakers",
                price= 22,
                image_url= "https://newbalance.ru/upload/iblock/697/iz997hht_nb_02_i.jpg"
            ),
        )
    )
    fun flash() = FlashDTO(
        listOf(
            ProductDiscountDTO(
                category = "Phones",
                name = "Samsung S10",
                price=1000,
                discount= 30,
                image_url= "https://www.dhresource.com/0x0/f2/albu/g8/M01/9D/19/rBVaV1079WeAEW-AAARn9m_Dmh0487.jpg"
            ),
            ProductDiscountDTO(
                category = "Games",
                name= "Sony Playstation 5",
                price = 700,
                discount= 30,
                image_url = "https://avatars.mds.yandex.net/get-mpic/6251774/img_id4273297770790914968.jpeg/orig"
            ),
            ProductDiscountDTO(
                category ="Games",
                name= "Xbox ONE",
                price=500,
                discount= 30,
                image_url="https://www.tradeinn.com/f/13754/137546834/microsoft-xbox-xbox-one-s-1tb-console-additional-controller.jpg"
            ),
            ProductDiscountDTO(
                category ="Cars",
                name= "BMW X6M",
                price= 35000,
                image_url= "https://mirbmw.ru/wp-content/uploads/2022/01/manhart-mhx6-700-01.jpg"
            ),
            ProductDiscountDTO(
                category= "Kids",
                name= "Reebok Classic",
                price= 24,
                discount= 30,
                image_url= "https://assets.reebok.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/3613ebaf6ed24a609818ac63000250a3_9366/Classic_Leather_Shoes_-_Toddler_White_FZ2093_01_standard.jpg"
            ),
            ProductDiscountDTO(
                category= "Kids",
                name= "New Balance Sneakers",
                price= 22,
                discount= 30,
                image_url= "https://newbalance.ru/upload/iblock/697/iz997hht_nb_02_i.jpg"
            ),
        )
    )
    fun brands() = LatestDTO(
        listOf(
            ProductDTO(
                category= "Kids",
                name= "New Balance Sneakers",
                price= 22,
                image_url= "https://newbalance.ru/upload/iblock/697/iz997hht_nb_02_i.jpg"
            ),
            ProductDTO(
                category= "Kids",
                name= "Reebok Classic",
                price= 24,
                image_url= "https://assets.reebok.com/images/h_2000,f_auto,q_auto,fl_lossy,c_fill,g_auto/3613ebaf6ed24a609818ac63000250a3_9366/Classic_Leather_Shoes_-_Toddler_White_FZ2093_01_standard.jpg"
            ),
            ProductDTO(
                category ="Cars",
                name= "BMW X6M",
                price= 35000,
                image_url= "https://mirbmw.ru/wp-content/uploads/2022/01/manhart-mhx6-700-01.jpg"
            ),
            ProductDTO(
                category ="Games",
                name= "Xbox ONE",
                price=500,
                image_url="https://www.tradeinn.com/f/13754/137546834/microsoft-xbox-xbox-one-s-1tb-console-additional-controller.jpg"
            ),

            ProductDTO(
                category = "Games",
                name= "Sony Playstation 5",
                price = 700,
                image_url = "https://avatars.mds.yandex.net/get-mpic/6251774/img_id4273297770790914968.jpeg/orig"
            ),
            ProductDTO(
                category = "Phones",
                name = "Samsung S10",
                price=1000,
                image_url= "https://www.dhresource.com/0x0/f2/albu/g8/M01/9D/19/rBVaV1079WeAEW-AAARn9m_Dmh0487.jpg"
            ),
        )
    )
}