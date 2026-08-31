package com.example.bookly.data.seeder

import com.example.bookly.domain.model.Book

object SoloLevelingData {
    val bookData: Pair<Book, List<Pair<String, String>>> = Book(
        title = "Solo Leveling (Tập 1)",
        author = "Chugong",
        description = "Sung Jin-Woo - Thợ săn hạng E yếu nhất thế giới bất ngờ sống sót qua thảm họa Hầm Ngục Đôi và nhận được giao diện game thăng cấp độc quyền, bắt đầu hành trình trở thành Thợ Săn Bóng Đêm mạnh nhất lịch sử.",
        category = "Hành Động / Hệ Thống",
        coverUrl = "https://static.wikia.nocookie.net/solo-leveling/images/8/84/Solo_Leveling_Novel_cover.png",
        rating = 4.9,
        pageCount = 450,
        publishedYear = 2018
    ) to listOf(
        "Chương 1: Hầm Ngục Kép Thảm Họa" to """
Sung Jin-Woo đứng run rẩy trước cánh cửa đá khổng lồ của Hầm Ngục Đôi. Là một "Thợ săn hạng E yếu nhất thế giới", mỗi lần bước vào hầm ngục, anh đều phải đánh đổi bằng cả tính mạng để kiếm tiền viện phí cho mẹ.

Cánh cửa đá nặng nề tự động đóng sập lại sau lưng nhóm thợ săn.

Bên trong căn phòng tế lễ rộng lớn là hàng chục bức tượng đá khổng lồ cao hàng chục mét. Ở trung tâm là Bức Tượng Thần ngồi trên ngai vàng với nụ cười ma quái tàn bạo.

"Xem kìa... Mắt của bức tượng đá đang chuyển động!" - Một thợ săn hoảng hốt gào lên.

Chưa kịp phản ứng, hai tia laser màu đỏ rực bắn ra từ đôi mắt của Bức Tượng Thần, thiêu rụi hàng loạt thợ săn thành tro bụi trong một chớp mắt!

Sức ép tâm linh khủng khiếp đè chặt mọi người xuống sàn nhà. 

Lần lượt từng luật lệ tàn khốc của căn phòng được hé lộ. Để sống sót, Jin-Woo đã dùng sự tinh ý của mình hướng dẫn mọi người cúi đầu trước Thần, nhảy múa trước các bức tượng cầm nhạc cụ. Nhưng ở luật lệ cuối cùng - "Buổi lễ hiến tế" - Jin-Woo đã bị thương nặng ở chân và bị bỏ lại một mình trên bàn thờ tế lễ.

Quái vật đá nâng chiếc thương khổng lồ lên, đâm xuyên qua ngực Jin-Woo.

Máu tươi bắn tung tóe. Ý thức của Jin-Woo lịm dần trong niềm căm hận và sự bất lực.

[Chúc mừng bạn đã hoàn thành tất cả các điều kiện ẩn của Quản Trị Viên.]
[Bạn đã nhận được quyền trở thành 'Người Chơi'. Bạn có đồng ý không?]

Một bảng thông báo giao diện màu xanh neon nổi lơ lửng trước mắt Jin-Woo giữa ranh giới sống chết...
        """.trimIndent(),

        "Chương 2: Thức Tỉnh Trong Bệnh Viện" to """
Jin-Woo mở mắt ra. Trần nhà màu trắng tinh khôi của phòng bệnh viện hiện ra trước mắt.

Mùi thuốc sát trùng quen thuộc xộc vào mũi. Anh giật mình ngồi bật dậy, đưa tay kiểm tra chân phải bị đứt lìa và vết thương đâm xuyên ngực tại Hầm Ngục Đôi.

Tất cả các vết thương nghiêm trọng đã biến mất hoàn toàn! Làn da lành lặn không một vết sẹo.

Các thành viên thuộc Hiệp Hội Thợ Săn bước vào phòng kiểm tra. Họ thông báo rằng khi lực lượng chi viện tới hiện trường Hầm Ngục Đôi, các bức tượng đá đã biến mất hoàn toàn và chỉ thấy Jin-Woo nằm ngất một mình trên bàn đá.

Họ nghi ngờ Jin-Woo trải qua đợt "Thức Tỉnh Lần Hai". Nhưng khi dùng máy đo lượng Mana, chỉ số của anh vẫn là 10 - mức E-Rank yếu nhất.

Sau khi mọi người rời đi, Jin-Woo tập trung ánh mắt vào không trung. 

Một bảng giao diện màu xanh neon công nghệ cao nổi lơ lửng ngay trước mặt anh:
[NHIỆM VỤ HÀNG NGÀY: CHUẨN BỊ CHO SỰ THĂNG CẤP]
- Chống đẩy: 0/100
- Gập bụng: 0/100
- Squat: 0/100
- Chạy bộ: 0/10km
[Cảnh báo: Nếu không hoàn thành nhiệm vụ trước 24:00, bạn sẽ bị phạt nặng.]
        """.trimIndent(),

        "Chương 3: Nhiệm Vụ Hàng Ngày" to """
Ban đầu, Jin-Woo nghĩ đây chỉ là một ảo giác do chấn thương tâm lý. Anh lờ đi bảng nhiệm vụ và nằm ngủ tiếp.

Đúng 24:00 đêm.

Bảng giao diện chuyển sang màu đỏ rực báo động! Căn phòng bệnh viện biến mất trong chớp mắt. 

Jin-Woo rơi xuống một sa mạc mênh mông bão cát. Một con rết khổng lồ Hạng D bò từ dưới lòng cát lên gầm rống đuổi theo anh!

Jin-Woo phải vắt chân lên cổ chạy bán sống bán chết suốt 4 tiếng đồng hồ trong sa mạc phạt để giữ mạng sống.

Khi trở lại phòng bệnh viện lúc 4:00 sáng, Jin-Woo thở hắt ra trong sự kiệt sức hoàn toàn. Anh hiểu rằng Hệ Thống này là có thực và vô cùng tàn nhẫn!

Từ hôm đó, Jin-Woo nghiêm túc thực hiện chuỗi bài tập mỗi ngày: 100 lần chống đẩy, 100 lần gập bụng, 100 lần squat và chạy 10km quanh công viên.

Sau mỗi lần hoàn thành nhiệm vụ, Hệ Thống thưởng cho anh:
1. Phục hồi toàn bộ thể lực.
2. 3 điểm chỉ số thuộc tính tự do.
3. 1 chiếc Rương Mật ngẫu nhiên.

Cơ thể yếu ớt của Jin-Woo bắt đầu thay đổi rõ rệt: cơ bắp săn chắc, chiều cao tăng lên, và ánh mắt trở nên vô cùng sắc bén!
        """.trimIndent(),

        "Chương 4: Chìa Khóa Hầm Ngục Thể Hiện" to """
Vào ngày thứ 7 hoàn thành nhiệm vụ daily, Jin-Woo mở chiếc Rương Thưởng và nhận được một vật phẩm đặc biệt: "Chìa khóa Hầm Ngục Chuyển Bùn Hạng E".

Mô tả vật phẩm hướng dẫn anh đến Ga tàu điện ngầm Hapjeong bị bỏ hoang.

Jin-Woo chuẩn bị một con dao găm giá rẻ mua tại cửa hàng trang bị, một chiếc balo chứa nước uống và tiến đến Ga Hapjeong lúc 23:00 đêm.

Anh cắm chiếc chìa khóa vào cánh cửa cuộn kim loại của ga tàu. 

Cánh cửa phát ra luồng sáng đỏ. Không gian xung quanh biến đổi thành một lối vào ngục tối riêng biệt. Bảng thông báo hiện lên:
[Bạn đã bước vào Hầm Ngục Riêng. Bạn không thể thoát ra ngoài cho đến khi tiêu diệt được Trùm Hầm Ngục hoặc tìm thấy cuộn giấy Dịch Chuyển.]

Jin-Woo bước xuống những bậc thang u tối của ga tàu điện ngầm. Mùi máu tươi và sát khí bắt đầu phảng phất trong không khí...
        """.trimIndent(),

        "Chương 5: Trận Chiến Với Vua Lycan" to """
Dưới lòng đất ga Hapjeong, đàn quái vật chó rồng "Steel-fanged Lycan" với đôi mắt đỏ quạch từ trong bóng tối lao ra tấn công Jin-Woo!

Nhờ chỉ số Tốc độ và Sức mạnh được cộng điểm hàng ngày, Jin-Woo di chuyển linh hoạt, né tránh những cú cắn chí mạng và dùng con dao găm đâm liên tiếp vào cổ quái vật.

[Bạn đã tiêu diệt Steel-fanged Lycan.]
[Kinh nghiệm +20, Bạn đã thăng cấp!]

Tiếng thông báo thăng cấp vang lên liên tục! Mỗi lần thăng cấp, thể lực và vết thương của Jin-Woo lại được hồi phục hoàn toàn.

Anh dấn sâu vào tầng dưới cùng và chạm trán Trùm Hầm Ngục: "Vua Lycan Răng Thép - Razan". Con quái vật khổng lồ với lớp lông đỏ như máu và sức mạnh áp đảo hoàn toàn các con quái vật trước.

Con dao găm của Jin-Woo bị gãy đôi khi đâm vào lớp da cứng như thép của Razan.

Trong giây phút sinh tử, Jin-Woo dồn toàn bộ điểm thuộc tính vào Sức Mạnh, tay không tóm chặt lấy hàm trên của Razan và bẻ gãy cổ con quái vật bằng một cú giật sấm sét!

[Bạn đã tiêu diệt Trùm Hầm Ngục Razan!]
[Bạn nhận được vật phẩm: 'Dao Găm Mọc Nanh Razan' & 'Áo Khoác Sát Thủ']
        """.trimIndent(),

        "Chương 6: Cuộc Săn Hầm Ngục Hạng C" to """
Để kiếm một khoản tiền lớn trả chi phí chăm sóc cho mẹ tại bệnh viện, Jin-Woo quyết định đăng ký tham gia một đội thợ săn tự do đi Hầm Ngục Hạng C.

Trưởng nhóm là Hwang Dong-Suk - một thợ săn Hạng C vạm vỡ, nụ cười xởi lởi nhưng ánh mắt lại ẩn giấu sự gian xảo. 

Đội hình gồm 8 thợ săn, cùng với Yoo Jin-Ho - một thiếu gia nhà giàu khoác trên mình bộ giáp vàng đắt tiền nhưng chưa từng có kinh nghiệm thực chiến.

Cô bạn thợ săn trị liệu Lee Joo-Hee (người từng sống sót cùng Jin-Woo tại Hầm Ngục Đôi) cũng tham gia. Cô lo lắng tiến lại gần Jin-Woo: "Anh Jin-Woo... Vết thương của anh thực sự đã lành rồi sao? Xin anh đừng mạo hiểm nữa..."

Jin-Woo mỉm cười nhẹ: "Cảm ơn cô, Joo-Hee. Tôi hiện tại đã khác trước rồi."

Cả đội tiến vào cánh cổng ngục tối Hạng C đặt tại một khu công trình xây dựng dở dang.
        """.trimIndent(),

        "Chương 7: Sự Phản Bội Trong Hang Động Mana" to """
Càng tiến sâu vào hầm ngục Hạng C, nhóm của Dong-Suk càng tỏ ra lười biếng. Họ để Jin-Woo và Yoo Jin-Ho đi tiên phong dò đường.

Ở tầng sâu nhất, nhóm phát hiện một hang động ngầm chứa đầy những khối quặng Mana khổng lồ phát sáng lấp lánh - trị giá hàng tỷ Won!

Ở trung tâm hang động là con quái vật Nhện Khổng Lồ Hạng C đang chìm trong giấc ngủ đông.

Dong-Suk giả vờ xởi lởi nói: "Hai cậu ở lại đây canh chừng hang động và con Nhện Trùm. Chúng tôi sẽ quay ra ngoài mang thiết bị khai thác quặng vào!"

Nói xong, Dong-Suk và 6 tên đồng mưu tháo chạy ra ngoài, kích hoạt khối thuốc nổ làm sập lối ra, giốt chặt Jin-Woo và Yoo Jin-Ho bên trong!

Yoo Jin-Ho hoảng hốt gào lên: "Chúng... chúng cố tình nhốt chúng ta để con Nhện Trùm tỉnh dậy ăn thịt! Sau đó chúng sẽ quay lại lấy toàn bộ quặng Mana mà không phải chia cho ai cả!"

Con Nhện Khổng Lồ mở 8 đôi mắt đỏ quạch, gầm lên một tiếng xé rách màng nhĩ...
        """.trimIndent(),

        "Chương 8: Tiêu Diệt Nhện Trùm" to """
Con Nhện Khổng Lồ Hạng C vung 8 chiếc chân nhọn như giáo sắt lao tới tấn công.

Yoo Jin-Ho sợ hãi ngã khụy xuống đất, giơ chiếc khiên vàng lên đỡ đòn trong sự tuyệt vọng.

Đột nhiên, một bóng đen di chuyển với tốc độ xé gió lướt qua!

BÙM!

Jin-Woo vung thanh 'Dao Găm Razan' chém đứt 2 chiếc chân của Nhện Trùm trong chớp mắt. Ánh mắt anh lạnh như băng, không một chút sợ hãi.

Jin-Woo kích hoạt kỹ năng [Lướt Nhanh], di chuyển xung quanh con quái vật như một bóng ma. Những nhát chém chính xác liên tục giáng xuống các khớp cơ của Nhện Trùm.

Con quái vật phun ra luồng tơ độc bao phủ toàn bộ hang động. Jin-Woo xoay người trên không, dồn toàn bộ sức mạnh nhảy lên lưng Nhện Trùm, đâm ngập lưỡi dao vào đốm mắt trung tâm của nó!

[Bạn đã tiêu diệt Nhện Khổng Lồ Hạng C.]
[Kinh nghiệm +1.200! Bạn đã thăng lên 5 cấp liên tiếp!]

Jin-Woo đứng trên xác con quái vật khổng lồ, luồng hào quang sức mạnh màu đen mờ nhạt bắt đầu bao bọc xung quanh thân thể anh...
        """.trimIndent(),

        "Chương 9: Nhiệm Vụ Tàn Sát Đột Xuất" to """
Tiếng nổ vách đá vang lên. Hwang Dong-Suk và 6 tên đồng mưu chui qua khe hở đi vào hang động để thu dọn chiến trường.

Chúng ngơ ngác bàng hoàng khi thấy con Nhện Trùm khổng lồ đã bị chém thành từng mảnh, còn Jin-Woo và Jin-Ho vẫn sống sót lành lặn!

Nét mặt Dong-Suk trở nên tàn bạo: "Kẻ yếu như mi làm sao hạ được nó? Chắc chắn là nhờ bộ giáp của thiếu gia Yoo! Rút kiếm ra! Không thể để hai tên này sống sót rời khỏi đây báo cảnh sát!"

7 tên thợ săn Rút kiếm bao vây lấy Jin-Woo và Jin-Ho.

Đột nhiên, không gian xung quanh Jin-Woo đóng băng. Bảng thông báo màu đỏ tươi báo động nguy cấp nổi lơ lửng:

[NHIỆM VỤ ĐỘT XUẤT: TIÊU DIỆT KẺ CÓ SÁT KHÍ]
- Số lượng kẻ địch: 7 người.
- Cảnh báo: Kẻ địch đang mang sát khí muốn sát hại bạn. Hãy tiêu diệt toàn bộ để bảo vệ bản thân.
- Án phạt nếu thất bại: Tim bạn sẽ ngừng đập ngay lập tức.
        """.trimIndent(),

        "Chương 10: Sát Thủ Bóng Đêm Xuất Hiện" to """
Jin-Woo nhắm mắt lại trong một giây. Hệ Thống này không cho phép anh nhân nhượng hay do dự. Nếu không giết kẻ thù, chính anh sẽ là người phải chết.

Khi Jin-Woo mở mắt ra, đôi mắt anh rực cháy luồng sát khí tím thẫm tàn bạo!

XẸT!

Trong chớp mắt, Jin-Woo xuất hiện ngay sau lưng tên thợ săn đứng gần nhất. Thanh dao găm ngọt ngào lướt qua cổ hắn trước khi hắn kịp chớp mắt.

"Cái... Cái gì?!" - Dong-Suk gào lên hoảng hốt.

Một cuộc tàn sát đơn phương diễn ra trong 10 giây! 

Jin-Woo di chuyển như một sát thủ bóng đêm huyền thoại, chém gục từng tên thợ săn phản bội. Dong-Suk quỳ xuống xin tha mạng, nhưng lưỡi dao của Jin-Woo đã lạnh lùng hạ xuống.

[Bạn đã hoàn thành Nhiệm vụ Đột xuất!]

Yoo Jin-Ho đứng chết trân ở góc hang động, toàn thân run rẩy vì chứng kiến sức mạnh vô song và sự tàn nhẫn của Jin-Woo.

Jin-Woo lau vết máu trên dao găm, quay lại nhìn Jin-Ho bằng ánh mắt thâm trầm: "Cậu có muốn tố cáo tôi không?"

Jin-Ho lập tức quỳ sụp xuống, dập đầu hô lớn: "Đại ca! Từ nay em xin thề đi theo Đại ca suốt đời!"

Hành trình trở thành Thợ Săn Bóng Đêm mạnh nhất lịch sử của Sung Jin-Woo chính thức bắt đầu...
        """.trimIndent()
    )
}
