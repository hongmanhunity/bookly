package com.example.bookly.data.seeder

import com.example.bookly.domain.model.Book

object SwordArtOnlineData {
    val bookData: Pair<Book, List<Pair<String, String>>> = Book(
        title = "Sword Art Online (Tập 1 - Aincrad)",
        author = "Reki Kawahara",
        description = "Năm 2022, 10.000 người chơi bị mắc kẹt trong thế giới thực tế ảo Sword Art Online bởi chiếc mũ NerveGear. Kirito - một kiếm sĩ cô độc phải chinh phục 100 tầng tháp Aincrad để giải cứu chính mình và mọi người.",
        category = "VRMMO / Hành Động",
        coverUrl = "https://static.wikia.nocookie.net/swordartonline/images/5/52/Sword_Art_Online_Volume_01.png",
        rating = 4.8,
        pageCount = 360,
        publishedYear = 2009
    ) to listOf(
        "Chương 1: Thế Giới Của Những Lưỡi Kiếm" to """
"Link Start!"

Tiếng hô vang lên, và toàn bộ ngũ quan của Kirito chìm vào khoảng không không trọng lực. Khi mở mắt ra, cậu đang đứng ở Quảng Trường Khởi Đầu của Aincrad - một tòa tháp khổng lồ bằng thép và đá bay lơ lửng giữa không trung với 100 tầng thử thách.

Đây là Sword Art Online, trò chơi VRMMO thực tế ảo hoàn toàn đầu tiên trên thế giới sử dụng chiếc mũ NerveGear.

Kirito - một Beta Tester từng tham gia thử nghiệm trò chơi - nhanh chóng quen với nhịp độ. Cậu hướng dẫn cho Klein, một người chơi mới gặp ở quảng trường, cách tung ra kỹ năng kiếm "Sword Skill" để kết liễu con quái vật Lợn Rừng ở đồng cỏ.

Đến 17:30 chiều, Klein muốn đăng xuất để đi ăn pizza. Nhưng khi mở menu hệ thống lên, nét mặt cậu chợt tái dại.

"Ơ... Kirito này... Nút Log Out (Đăng xuất) đâu mất rồi?"

Kirito giật mình kiểm tra menu của mình. Đúng vậy. Góc dưới bên trái nơi đặt nút Đăng xuất hoàn toàn trống rỗng.

Đột nhiên, tiếng chuông cảnh báo vang lên inh ỏi khắp thế giới. Bầu trời Aincrad nhuộm một màu đỏ máu. Một sinh thể khổng lồ khoác áo bào đỏ rực xuất hiện lơ lửng trên không trung.

Đó là Akihiko Kayaba - người sáng tạo ra SAO và NerveGear:

"Chào mừng các người chơi đến với thế giới của tôi. Việc không thể đăng xuất không phải là lỗi hệ thống. Đây là tính năng mặc định của Sword Art Online.

Từ thời điểm này, các bạn không thể tự thoát ra ngoài. Nếu ai đó ở thế giới thực cố tình tháo NerveGear ra khỏi đầu bạn, chiếc mũ sẽ phát sóng vi ba thiêu rụi não bộ của bạn ngay lập tức. Nếu thanh HP của bạn về 0 trong game, bạn cũng sẽ thực sự tử vong ngoài đời thực.

Cách duy nhất để thoát khỏi đây: Chinh phục tầng 100 của tháp Aincrad!"

Một làn sóng kinh hoàng bao trùm lên 10.000 con người. Cuộc chiến sinh tồn tàn khốc chính thức bắt đầu...
        """.trimIndent(),

        "Chương 2: Cuộc Thảm Sát Tháng Đầu Tiên" to """
Trong tháng đầu tiên sau thông báo chấn động của Akihiko Kayaba, thảm kịch tồi tệ nhất lịch sử nhân loại đã xảy ra tại thế giới ảo Aincrad.

Hơn 2.000 người chơi đã trút hơi thở cuối cùng. Hàng trăm người tự sát vì hoảng loạn tâm lý không thể chịu đựng được thực tại tàn khốc. Hàng ngàn người khác thiệt mạng vì dấn thân vào các khu vực quái vật nguy hiểm khi chưa đủ cấp độ, hoặc bị phục kích bởi các bẫy ngục tối.

Không khí tuyệt vọng bao trùm lấy Quảng Trường Khởi Đầu. Hàng ngàn người chơi co cụm lại trong các quán trọ, không dám bước chân ra ngoài vùng an toàn (Safe Zone) vì sợ cái chết.

Kirito - với tư cách là một Beta Tester - hiểu rõ rằng nếu mọi người chỉ co cụm lại chờ đợi, tài nguyên sẽ cạn kiệt và tất cả sẽ chết dần chết mòn. 

Cậu đưa ra một quyết định tàn nhẫn với chính bản thân mình: Rời bỏ người bạn mới Klein và Quảng Trường Khởi Đầu, một mình tiến vào vùng hoang dã để cày cấp độ, thu thập trang bị và tích lũy thông tin về các ngục tối.

Đêm đó, dưới ánh trăng ảm đạm của Aincrad, Kirito siết chặt thanh kiếm đơn trên lưng, bước đi một mình trên con đường cô độc...
        """.trimIndent(),

        "Chương 3: Cuộc Họp Trinh Sát Tầng 1" to """
Sau một tháng bế tắc, cuộc họp chiến lược chinh phục Trùm Tầng 1 (Floor Boss) cuối cùng cũng được tổ chức tại một giảng đường ngoài trời thuộc Làng Tolbana.

Người đứng ra triệu tập cuộc họp là Diabel - một kiếm sĩ tự xưng là Beta Tester, khoác trên mình bộ giáp bạc lộng lẫy và phong thái lãnh đạo đầy nhiệt huyết.

"Mọi người! Hôm nay đội trinh sát của tôi đã tìm thấy Phòng Trùm của Tầng 1 ở đỉnh ngục tối!" - Diabel hô lớn trước sự reo hò của hơn 40 kiếm sĩ - "Chúng ta phải đánh bại con Trùm này để mở đường lên Tầng 2, chứng minh cho 8.000 người chơi còn lại thấy rằng trò chơi này hoàn toàn có thể phá đảo!"

Diabel yêu cầu mọi người lập thành các đội 6 người (Party). 

Kirito ngồi ở hàng ghế cuối một mình. Cậu quay sang nhìn xung quanh và phát hiện một người chơi khác cũng đang ngồi cô độc. Đó là một cô gái quấn chiếc áo bào trùm đầu màu xám kín mịch, chỉ để lộ đôi môi mỏng xinh xắn.

"Này, cô cũng bị lẻ đội đúng không? Có muốn lập tổ đội với tôi không?" - Kirito chủ động lên tiếng.

Cô gái im lặng gật đầu, chấp nhận lời mời kết đội. Trên thanh trạng thái hiện lên tên của cô: Asuna.

Kirito nhìn xuống tay Asuna và ngạc nhiên phát hiện cô đang cầm một thanh kiếm Liễu (Rapier) cực kỳ đắt tiền, nhưng phong thái lại hoàn toàn ngơ ngác như một người chưa từng chơi game VRMMO...
        """.trimIndent(),

        "Chương 4: Trận Chiến Trùm Tầng 1" to """
Sáng hôm sau, biệt đội 44 kiếm sĩ tiến vào sào huyệt của Trùm Tầng 1: Illfang the Kobold Lord.

Căn phòng ngục tối cao vút tối om. Đột nhiên, bốn ngọn đuốc bùng cháy rực rỡ. Con quái vật Kobold khổng lồ cao hơn 3 mét với làn da xanh xám, tay cầm thanh đao phay và khiên bảo vệ, xung quanh là 3 con quái vật Ruin Kobold Sentinel gầm lên dữ dội!

"Tấn công!" - Diabel vung kiếm phát lệnh.

Các nhóm tiên phong xông lên thu hút sự chú ý của quái vật cận vệ. Kirito và Asuna phối hợp ăn ý nhịp nhàng. Asuna di chuyển với tốc độ đâm kiếm liễu nhanh như chớp, tung ra kỹ năng "Linear" chính xác vào điểm yếu của quái vật.

Khi thanh HP của Illfang chuyển sang màu đỏ nguy hiểm (dưới 10%), nó vứt thanh đao phay và khiên xuống, rút ra một thanh đại đao Nodachi khổng lồ từ sau lưng.

Diabel hô lớn: "Mọi người lùi lại! Để tôi giáng đòn kết liễu (Last Attack)!"

Kirito giật mình nhận ra điều bất thường: Trong phiên bản Beta, con Trùm sẽ rút ra một thanh kiếm Talwar, nhưng ở bản chính thức này, Kayaba đã thay đổi vũ khí thành đại đao Nodachi với chuỗi kỹ năng hoàn toàn khác!

"Diabel! Dừng lại! Lùi lại ngay!" - Kirito gào lên.

Nhưng đã quá trễ. Illfang nhảy lên không trung, tung kỹ năng kiếm đại đao chém dọc một đường sấm sét xuống người Diabel. Thanh HP của Diabel giảm về 0 trong chớp mắt.

Diabel ngã xuống tay Kirito. Trước khi thân thể hóa thành những mảnh tinh thể vỡ vụn, ông thì thầm: "Xin lỗi... Hãy cứu lấy mọi người... Kirito..."

Kirito rực cháy ngọn lửa căm hờn. Cậu rút kiếm ra, cùng Asuna lao vào quyết chiến, tung ra kỹ năng kiếm liên hoàn kết liễu Illfang, mở ra cánh cửa lên Tầng 2!
        """.trimIndent(),

        "Chương 5: Biệt Danh Beater" to """
Con Trùm Tầng 1 đổ gục. Cánh cửa đá khổng lồ dẫn lên Tầng 2 chậm rãi mở ra.

Tuy nhiên, niềm vui chiến thắng bị dập tắt ngay lập tức. Kibaou - một kiếm sĩ thuộc nhóm tiên phong - bước ra chỉ tay vào mặt Kirito với thái độ giận dữ:

"Tên này! Cậu biết rõ con Trùm có kỹ năng mới nhưng lại không nói cho Diabel! Cậu là Beta Tester cố tình để Diabel chết để cướp vật phẩm hiếm Last Attack đúng không?!"

Một làn sóng nghi ngờ và căm ghét từ các người chơi mới bắt đầu hướng về phía Kirito. Họ gào lên chửi rủa các Beta Tester là những kẻ ích kỷ và dối trá.

Kirito đứng lặng yên bên cạnh Asuna. Cậu hiểu rằng nếu tình trạng nghi ngờ này kéo dài, mâu thuẫn giữa các Beta Tester và người chơi mới sẽ làm sụp đổ hoàn toàn lực lượng chinh phạt.

Kirito bật cười khoái trá - một tiếng cười nham hiểm và kiêu ngạo tự tạo:

"Hahaha! Đừng so sánh tôi với đám Beta Tester gà mờ đó! Trong đợt thử nghiệm, tôi đã leo tới tầng 50! Tôi biết tất cả các vị trí bẫy, trùm và nhiệm vụ mà đám người chơi mới các người chưa từng mơ tới!"

Kibaou lùi lại hoảng hốt: "Cái... Cái gì?! Cậu là một Beta Tester và là một Cheater!"

"Đúng vậy," - Kirito khoác chiếc áo bào đen 'Coat of Midnight' vừa nhận được từ Last Attack lên người - "Tôi không phải Beta Tester thông thường. Tôi là một BEATER (Beta Cheater)! Từ nay về sau, đừng nhầm lẫn tôi với họ."

Kirito quay lưng bước đi lên cầu thang Tầng 2. Asuna đuổi theo: "Sao cậu lại phải gánh chịu sự căm thù của tất cả mọi người như vậy?"

Kirito mỉm cười nhẹ: "Vì đó là cách duy nhất để họ đoàn kết lại. Hãy tiếp tục mạnh mẽ hơn nhé, Asuna."
        """.trimIndent(),

        "Chương 6: Hội Mèo Đen Ngực Đỏ" to """
Nhiều tháng trôi qua. Kirito che giấu cấp độ thật của mình và sống như một kiếm sĩ bình thường tại các tầng giữa của Aincrad.

Trong một lần giải cứu một nhóm người chơi khỏi bẫy quái vật, Kirito được mời gia nhập một hội nhỏ gồm những bạn trẻ ngoài đời thực là bạn học cùng câu lạc bộ máy tính: "Hội Mèo Đen Ngực Đỏ".

Hội trưởng Keita vui vẻ chào đón Kirito. Các thành viên Tetsuo, Sasamaru, Ducker và đặc biệt là cô gái Sachi - người sử dụng thương - đều dành cho Kirito sự quý mến chân thành.

Sachi là một cô gái cực kỳ nhút nhát. Đêm đêm, cô thường ngồi gục đầu ở ban công quán trọ, run rẩy vì sợ hãi cái chết: "Kirito-kun... Liệu chúng ta có thực sự sống sót để trở về thế giới thực không?"

Kirito ngồi xuống bên cạnh Sachi, nhẹ nhàng nắm lấy tay cô: "Tôi hứa với cô, Sachi. Cô là một thành viên mạnh mẽ. Tôi sẽ bảo vệ cô và tất cả mọi người cho đến ngày chúng ta phá đảo trò chơi này."

Lần đầu tiên kể từ khi bị mắc kẹt ở SAO, Kirito tìm lại được cảm giác ấm áp của một gia đình...
        """.trimIndent(),

        "Chương 7: Bi Kịch Trong Hầm Ngục" to """
Vào một ngày giữa năm, hội trưởng Keita đi đến thị trấn chính để mua một căn nhà hội cố định. Các thành viên còn lại quyết định tiến vào hầm ngục Tầng 27 để cày thêm tiền mua nội thất.

Kirito cảm thấy có điều bất an, nhưng vì muốn giữ niềm vui cho mọi người, cậu đã không quyết liệt ngăn cản.

Tại tầng sâu của ngục tối, Ducker phát hiện một chiếc rương kho báu nằm ở trung tâm căn phòng đá. Cậu ta hào hứng lao tới mở rương trước lời cảnh báo của Kirito.

"BẪY (TRAP)!" - Kirito gào lên.

Cánh cửa đá nặng nề lập tức đóng sập lại! Căn phòng nhuộm một màu đỏ rực báo động. Không gian ngục tối ngăn chặn hoàn toàn việc sử dụng Dịch Chuyển Tinh Thể (Teleport Crystal).

Hàng chục con quái vật Skeleton Chieftain và Dark Dwarf xuất hiện dày đặc, bao vây lấy nhóm Mèo Đen.

Kirito rút kiếm chiến đấu điên cuồng, tung ra những kỹ năng cao cấp nhất để dọn dẹp quái vật. Nhưng số lượng quái vật quá đông.

Lần lượt từng thành viên gục ngã. 

Và trước mắt Kirito, con quái vật Skeleton vung lưỡi rìu khổng lồ chém xuyên qua ngực Sachi.

"Kirito-kun... Cảm ơn cậu..." - Sachi mỉm cười nước mắt nhòa lệ trước khi thân thể cô tan biến thành những mảnh tinh thể màu lam lấp lánh trong không trung.

Kirito gục xuống sàn đá, hai tay ôm lấy khoảng không trống rỗng, tiếng gào khóc đau đớn xé rách màn đêm...
        """.trimIndent(),

        "Chương 8: Giáng Sinh Đen Và Vật Phẩm Hồi Sinh" to """
Đêm Giáng Sinh tại Tầng 35. Tuyết rơi dày đặc bao phủ những dòng sông băng lạnh giá.

Kirito sống như một hồn ma trong suốt 6 tháng qua. Cậu lang thang khắp nơi tìm kiếm một tin đồn thần thoại: Con Trùm Giáng Sinh "Nicholas Nicholas" xuất hiện dưới cây thông noel khổng lồ sẽ rơi ra vật phẩm hồi sinh người chết.

Argo The Rat - thông tin viên lừng danh - đã trao cho Kirito vị trí xuất hiện của con Trùm kèm lời cảnh báo: "Nó quá mạnh đối với một người chơi đơn độc. Cậu sẽ chết đấy!"

Kirito thản nhiên bước vào bão tuyết. Đối với cậu lúc này, cái chết không còn đáng sợ bằng việc sống trong sự dằn vọt vì không bảo vệ được Sachi.

Trận chiến dưới gốc cây thông noel diễn ra tàn khốc. Kirito chịu hàng loạt vết chém chí mạng, thanh HP rơi xuống vùng nguy hiểm. Nhưng bằng sự cuồng nộ và tuyệt vọng, cậu liên tục chém đứt thân thể Nicholas Nicholas cho đến khi nó nổ tung.

Một vật phẩm lấp lánh rơi xuống tay Kirito: "Divine Stone of Retracing Soul" (Thạch Linh Hồi Hồn).

Tuy nhiên, khi đọc dòng mô tả vật phẩm, trái tim Kirito hoàn toàn vỡ vụn:
[Vật phẩm chỉ có hiệu lực hồi sinh người chơi trong vòng 10 GIÂY sau khi tử vong.]

Sachi đã chết cách đây 6 tháng.

Klein cùng nhóm người hội Fuurinkazan chạy tới hiện trường. Kirito lẳng lặng ném viên đá hồi sinh cho Klein, rồi gục đầu xuống tuyết trắng khóc nấc lên từng hồi.

Đúng 00:00 đêm Giáng Sinh, chiếc đồng hồ báo tin nhắn của Kirito vang lên. Một tin nhắn ghi âm tự động do Sachi cài đặt trước khi chết vang lên trong tai nghe của cậu:

"Chào Kirito-kun... Khi cậu nghe được tin nhắn này, có lẽ tớ đã không còn nữa. Nhưng xin cậu đừng tự dằn vọt bản thân. Tớ đã rất hạnh phúc khi được gặp cậu... Hãy sống sót nhé, Kirito..."
        """.trimIndent(),

        "Chương 9: Tái Ngộ Asuna Tại Tầng 74" to """
 Hai năm trôi qua. Kirito giờ đây đã đạt cấp độ 96 và trở thành "Kiếm Sĩ Đen" lừng danh khắp Aincrad.

Tại vùng hoang dã Tầng 74, Kirito hạ gục một con Thỏ Rừng Thần Thoại Hạng S - nguồn nguyên liệu chế biến món ăn ngon nhất thế giới game.

Cậu tình cờ tái ngộ Asuna - lúc này đã trở thành Phó Hội Trưởng kiêm "Tia Chớp" của Hội Huyết Kế Thần Sĩ (Knights of the Blood Oath), hội mạnh nhất Aincrad.

Asuna mỉm cười rạng rỡ: "Cậu có nguyên liệu Thỏ Rừng sao? Hãy đến nhà tôi tại Tầng 61, tôi sẽ nấu cho cậu ăn!"

Tại căn nhà gỗ ấm cúng của Asuna, bữa ăn ngon lành đã kéo hai tâm hồn mệt mỏi lại gần nhau. Asuna tâm sự về những áp lực khi phải gánh vác trọng trách lãnh đạo hội và khao khát được trở về thế giới thực.

"Kirito-kun, hãy lập đội với tôi trong đợt trinh sát Tầng 74 nhé!" - Asuna mỉm cười, đôi mắt thạch anh sáng rực niềm tin.
        """.trimIndent(),

        "Chương 10: Ác Mộng Ác Ma Ánh Sáng Mắt Xanh" to """
Trận chiến ngục tối Tầng 74 bùng nổ thảm họa.

Đội quân trinh sát của quân đội Quân Đoàn (The Army) do Cobatz dẫn đầu đã tự ý xông vào Phòng Trùm khi chưa có sự chuẩn bị đầy đủ.

Con Trùm Tầng 74 "The Gleam Eyes" - một ác quỷ xanh khổng lồ mang đầu dê, tay cầm thanh đại đao Zamber - đang tàn sát dã man các binh sĩ. Căn phòng ngục tối một lần nữa bị cấm Dịch Chuyển Tinh Thể.

Nhìn thấy các binh sĩ gục ngã liên tục, Asuna rút kiếm liễu lao vào ứng cứu. Kirito gào lên: "Asuna! Lùi lại!"

Kirito xông ra chắn trước mặt Gleam Eyes. Thanh đao khổng lồ của con Trùm giáng xuống làm thanh HP của Kirito tuột dốc không phanh.

Không còn lựa chọn nào khác để cứu lấy người mình yêu, Kirito nghiến răng gào lớn:

"ASUNA! KLINE! BẢO VỆ TÔI TRONG 10 GIÂY!"

Kirito lùi lại, mở menu hệ thống và kích hoạt kỹ năng độc quyền bí mật mà cậu chưa từng tiết lộ cho bất kỳ ai:

KỸ NĂNG NĂNG LỰC ĐỘC QUYỀN: DUAL BLADES (SONG KIẾM)!

Kirito rút thanh kiếm thứ hai - thanh ma kiếm màu xanh ngọc 'Elucidator' và thanh băng kiếm 'Dark Repulser' - từ sau lưng ra.

Vũ điệu Song Kiếm cuồng phong bùng nổ! Kirito di chuyển với tốc độ không thể nhìn thấy bằng mắt thường, tung ra kỹ năng 16 nhát chém liên hoàn "Starburst Stream" xé rách không khí!

Âm thanh kim loại va chạm giòn giã. Sau 16 nhát chém chí mạng, Gleam Eyes nổ tung thành hàng ngàn mảnh tinh thể lấp lánh. 

Kirito đứng thở dốc với 1 HP còn lại, gục vào vòng tay nước mắt nhòa lệ của Asuna trước sự bàng hoàng thán phục của toàn thể người chơi...
        """.trimIndent()
    )
}
