package com.example.bookly.data.seeder

import com.example.bookly.domain.model.Book

object ClassroomOfTheEliteData {
    val bookData: Pair<Book, List<Pair<String, String>>> = Book(
        title = "Classroom of the Elite (Tập 1)",
        author = "Shōgo Kinugasa",
        description = "Trường THPT Năng khiếu Kōdo Ikusei là ngôi trường mộng mơ với tỷ lệ đỗ đại học 100%. Ayanokōji Kiyotaka - nam sinh thích thu mình bị xếp vào Lớp 1-D, nơi tập hợp những học sinh bị coi là phế thải. Cùng Horikita Suzune, cuộc chiến trí tuệ sinh tồn bắt đầu.",
        category = "Học Đường / Trí Tuệ",
        coverUrl = "https://static.wikia.nocookie.net/youkoso-jitsuryoku-shijou-shugi-no-kyoushitsu-e/images/c/c1/LN_Vol_01_cover.jpg",
        rating = 4.9,
        pageCount = 340,
        publishedYear = 2015
    ) to listOf(
        "Chương 1: Cấu Trúc Của Xã Hội Thu Nhỏ" to """
Con người sinh ra có thực sự bình đẳng?

Nếu bạn hỏi một nhà triết học hay một chính trị gia, họ sẽ mỉm cười và trả lời rằng: "Mọi người đều bình đẳng trước pháp luật và có quyền tự do như nhau". Nhưng thực tế có phải như vậy không? Nếu một đứa trẻ sinh ra trong một gia đình giàu có và một đứa trẻ sinh ra ở khu ổ chuột, liệu xuất phát điểm của chúng có bình đẳng? Nếu một người sinh ra với chỉ số IQ 160 và một người bị khuyết tật trí tuệ, liệu tương lai của họ có như nhau?

Câu trả lời là KHÔNG. Xã hội loài người từ khi bắt đầu nền văn minh đã chưa bao giờ có sự bình đẳng tuyệt đối. Từ ngữ "Bình đẳng" chỉ là một lời dối trá ngọt ngào được tạo ra để xoa dịu những kẻ yếu thế.

Tôi ngồi ở hàng ghế cuối của chiếc xe buýt tuyến Kōdo Ikusei. Gió xuân nhẹ nhàng thổi qua khe cửa kính nửa mở, mang theo hương thơm nhè nhẹ của hoa anh đào vừa nở rộ. 

Trước mặt tôi là một khung cảnh khá quen thuộc trên các phương tiện công cộng: một cụ bà tóc bạc phơ, lưng đã còng đang phải gượng đứng bám vào tay đu quay, chiếc xe buýt chao đảo theo từng nhịp phanh khiến bà suýt ngã vài lần.

Trong khi đó, ở hàng ghế ưu tiên dành cho người già và phụ nữ mang thai, một nam sinh tóc nhuộm vàng hoe với vẻ ngoài ngông cuồng vẫn thản nhiên vắt chân chữ ngũ. Cậu ta đeo một chiếc tai nghe đắt tiền, mắt chằm chằm nhìn vào màn hình điện thoại và nhún nhảy theo giai điệu nhạc rock.

"Cậu kia, cậu không thấy cụ bà đang phải đứng rất vả sao? Cậu có thể nhường ghế được không?"

Một cô gái ngồi ở hàng ghế đối diện cất tiếng. Cô có mái tóc đen dài mượt mà xõa xuống tận thắt lưng, đôi mắt màu nâu trầm toát lên vẻ thông minh nhưng cực kỳ lạnh lùng. Đó là Horikita Suzune - người sau này ngồi ngay bên cạnh tôi trong lớp học.

Nam sinh tóc vàng từ từ tháo một bên tai nghe ra, nhếch môi nở một nụ cười nham hiểm và kiêu ngạo:

"Nhường ghế? Tại sao tôi phải làm vậy? Trong xã hội này, người mạnh sẽ chiếm lấy đặc quyền. Tôi đến trước, tôi trả tiền vé, tôi có quyền ngồi. Bà cụ già yếu không thể đứng vững là do quy luật tự nhiên của tuổi tác, tôi không có bất kỳ nghĩa vụ đạo đức nào phải gánh vác sự yếu đuối của người khác cả."

"Cậu... cậu không cảm thấy xấu hổ với tư cách là một học sinh sắp bước vào ngôi trường danh giá này sao?" - Horikita nhíu mày, tông giọng hạ thấp tỏ rõ sự khinh bỉ.

"Xấu hổ? Hahaha! Để tôi giới thiệu cho cô biết, tôi là Kōenji Rokusuke, người thừa hưởng tập đoàn Kōenji. Thời gian và sự thoải mái của tôi đắt giá hơn sự thương hại rẻ tiền đó nhiều."

Cuộc tranh luận kéo dài vài phút mà không có kết quả. Những hành khách xung quanh chỉ biết xầm xì bàn tán nhưng không ai dám can thiệp. Cuối cùng, một nhân viên văn phòng tốt bụng đứng ở phía sau đã chủ động bước đến nhường ghế cho cụ bà.

Tôi thở dài một hơi nhẹ, quay mặt nhìn ra ngoài cửa sổ xe buýt. Ngôi trường THPT Năng khiếu Kōdo Ikusei đã hiện ra trước mắt. Nơi đây được chính phủ Nhật Bản thành lập với ngân sách hàng trăm tỷ Yên nhằm đào tạo những nhân tài kiệt xuất nhất cho tương lai quốc gia. Tỷ lệ học sinh đỗ đại học hàng đầu và có việc làm ngay sau khi tốt nghiệp là 100%.

Không chỉ vậy, học sinh ở đây được sống trong một khu đô thị khép kín hiện đại với trung tâm thương mại, rạp chiếu phim, nhà hàng sang trọng. Mỗi tháng, mỗi học sinh được cấp 100.000 điểm tương đương 100.000 Yên (khoảng 18 triệu VNĐ) để chi tiêu hoàn toàn tự do.

Một thiên đường thực sự dành cho giới trẻ?

Không. Bản năng mách bảo tôi rằng thiên đường đó không bao giờ dành cho tất cả mọi người.

Tôi tên là Ayanokōji Kiyotaka. Một học sinh với điểm số thi đầu vào trung bình một cách kỳ lạ: đúng 50 điểm cho tất cả các môn Toán, Văn, Anh, Lý, Hóa. Tôi vừa trúng tuyển vào Lớp 1-D - nơi chứa đựng những học sinh bị coi là 'vấn đề' và 'phế thải' của khóa học. Cuộc chiến sinh tồn thực sự bây giờ mới chính thức bắt đầu...
        """.trimIndent(),

        "Chương 2: Quyền Lực Của Những Con Số Point" to """
Tiếng chuông báo bắt đầu buổi học đầu tiên vang lên giòn giã.

Thầy giáo chủ nhiệm Chabashira Sae bước vào lớp với bộ trang phục công sở màu xám tối. Gương mặt cô lạnh như băng, không một chút nụ cười thân thiện hay lời chào mừng xã giao nào. 

Cô dừng lại trước bục giảng, ánh mắt sắc như dao quét qua một lượt 40 học sinh của Lớp 1-D. Sau đó, cô lẳng lặng lấy viên phấn trắng, viết lên bảng đen dòng chữ in hoa lớn:

"SỐ ĐIỂM BAN ĐẦU: 100.000 S-POINT"

Cả phòng học Lớp 1-D ngay lập tức bùng nổ trong tiếng reo hò ầm ĩ!

"Thật... thật không thể tin nổi! 100.000 Yên mỗi tháng! Ngôi trường này chính là thiên đường!" - Ike Kanji, một nam sinh năng nổ ngồi bàn đầu gào lên sung sướng.

Yamauchi Haruki bên cạnh cũng đập tay ăn mừng: "Tuần này tớ phải mua ngay chiếc máy chơi game PlayStation 5 mới nhất! Đúng là không phí công sức chuyển vào đây!"

Mọi người bắt đầu bàn tán xôn xao về việc sẽ mua sắm những gì: từ quần áo hàng hiệu, mỹ phẩm đắt tiền, đến những bữa ăn đồ nướng sang trọng tại trung tâm thương mại của trường. 

Chỉ có Horikita Suzune ngồi ngay bên cạnh tôi là vẫn giữ nguyên nét mặt trầm ngâm, đôi mày liễu nhíu lại đầy nghi vấn.

"Ayanokōji-kun, cậu không thấy có gì đó bất thường sao?" - cô ấy quay sang thì thầm với tôi.

"Bất thường? Chẳng phải nhà trường rất hào phóng sao? Cho học sinh cấp ba 100.000 Yên mỗi tháng để tiêu xài tự do." - tôi đáp với giọng điệu bình thản và ngơ ngác nhất có thể.

"Một tổ chức giáo dục do chính phủ tài trợ không bao giờ phát tiền không cho ai cả. 100.000 điểm này là phần thưởng... hoặc là một chiếc bẫy thử nghiệm tâm lý."

Horikita nói hoàn toàn đúng. Nhưng hầu hết những học sinh trong Lớp D lúc này đều đang bị mờ mắt bởi đống tiền khổng lồ trên trời rơi xuống. 

Và những ngày tiếp theo là một chuỗi thảm họa về tính tự giác.

Trong suốt tháng 4, các học sinh Lớp D bắt đầu bộc lộ bản chất. Họ đi học muộn liên tục, nói chuyện riêng oang oang trong giờ học, thản nhiên lướt điện thoại, chơi game, thậm chí gục đầu xuống bàn ngủ ngáy sấm sét. 

Thầy cô giáo đứng trên bục giảng nhìn tất cả những hành vi vi phạm kỷ luật đó nhưng tuyệt nhiên KHÔNG HỀ nhắc nhở, phạt hay gạch tên bất kỳ ai. Họ chỉ nhìn Lớp D với ánh mắt thương hại và lạnh lùng rồi tiếp tục giảng bài.

Rồi ngày 1 tháng 5 - ngày đầu tiên của tháng tiếp theo cũng đã đến.

Tất cả học sinh Lớp D đứng chật kín trước máy rút tiền ATM và liên tục làm mới ứng dụng trên điện thoại để chờ đón 100.000 điểm tiếp theo đổ về tài khoản.

9:00 sáng. Thông báo biến động số dư vang lên.

Tất cả học sinh nhìn vào màn hình điện thoại của mình. Nét mặt reo hò rạng rỡ ngay lập tức biến thành sự bàng hoàng, tột cùng hoảng sợ!

Số dư được cộng vào tài khoản của toàn bộ học sinh Lớp D là: 0 POINT.

Thầy Chabashira đứng trên bục giảng, nở một nụ cười nham hiểm và lạnh ngắt mà tôi chưa từng thấy trước đây. Cô thong thả gõ cây thước kẻ xuống bàn:

"Hệ thống không hề bị lỗi. Các em đúng là những đứa trẻ ngây thơ và ngu ngốc. Ngay từ ngày đầu tiên, tôi đã nói 100.000 điểm đại diện cho giá trị của các em. Số điểm hàng tháng được tính dựa trên đánh giá thái độ, kỷ luật và năng lực học tập của từng lớp."

Thầy Chabashira bật máy chiếu lên bảng. Một bảng thống kê chi tiết xuất hiện:
- Vi phạm nói chuyện riêng trong giờ: 98 lần (-9.800 điểm)
- Đi học muộn: 45 lần (-4.500 điểm)
- Sử dụng điện thoại trong giờ: 62 lần (-6.200 điểm)
- Ngủ gật: 75 lần (-7.500 điểm)

"Tổng số điểm kỷ luật bị trừ của Lớp D trong tháng qua là 100.000 điểm. Số điểm còn lại của các em chính xác là 0 POINT. Trong tháng này, các em sẽ phải sống mà không có một xu dính túi. Chào mừng các em đến với thực tế phũ phàng của Lớp D - lớp của những phế thải!"

Cả phòng học chìm trong sự câm nín và tuyệt vọng bao trùm. Cuộc chiến đẳng cấp tại Kōdo Ikusei chính thức bắt đầu...
        """.trimIndent(),

        "Chương 3: Sự Thật Về Đánh Giá Kỷ Luật" to """
Sự bàng hoàng và tuyệt vọng gặm nhấm tâm trí của 40 học sinh Lớp D.

Những tiếng thở dài, những ánh mắt thẫn thờ xuất hiện ở khắp các góc phòng học. Ike Kanji gục đầu xuống bàn khóc rống lên: "Tớ đã lỡ tiêu sạch 100.000 điểm tháng trước vào quần áo và đồ ăn rồi! Giờ đến cả tiền mua một gói mì ramen ăn qua ngày tớ cũng không có!"

Yamauchi Haruki bên cạnh cũng méo mặt: "Nhà trường có rủ lòng thương cho chúng ta vay tiền không nhỉ? Chứ thế này thì sống làm sao nổi trong 30 ngày tới?!"

Cô Chabashira đứng trên bục giảng nhếch môi: "Nhà trường vẫn dành một khoản trợ cấp tối thiểu. Ở căng tin có cung cấp các suất ăn miễn phí bao gồm cơm trắng và canh miso không gia vị dành cho những học sinh hết điểm. Các em có thể đăng ký ăn hàng ngày để duy trì sự sống."

Cả lớp thở dài thốt lên những tiếng rên rỉ cay đắng. Ăn cơm trắng với nước canh miso nhạt nhẽo suốt một tháng trời? Đó chẳng khác nào một án phạt tù tày trời!

Sau giờ học, Horikita Suzune chủ động gọi tôi ra dãy hành lang vắng người ở tầng 3.

Gió chiều thổi tung mái tóc đen mượt của cô. Đôi mắt nâu trầm nhìn xoáy vào tôi với sự kiên định lạ thường:

"Ayanokōji-kun, tôi không chấp nhận chuyện này. Tôi đến ngôi trường này là để vươn lên Lớp A, không phải để thối rữa ở cái nơi gọi là phế thải Lớp D này."

"Vươn lên Lớp A?" - Tôi đáp nhẹ nhàng - "Cô có biết khoảng cách giữa các lớp là khủng khiếp thế nào không? Lớp A tháng này được cộng 94.000 điểm, Lớp B là 84.000 điểm, Lớp C là 49.000 điểm. Còn Lớp D chúng ta là 0 điểm. Muốn vượt qua họ, Lớp D phải đạt điểm tuyệt đối trong tất cả các bài kiểm tra và duy trì kỷ luật thép."

"Tôi biết điều đó rất khó," - Horikita mím chặt môi, nắm chặt hai bàn tay nhỏ bé lại - "Nhưng nếu không thử, chúng ta sẽ mãi mãi là những kẻ thất bại. Cậu có muốn hợp tác với tôi không?"

Tôi nhìn vào mắt Horikita. Cô gái này sở hữu năng lực học tập và tư duy vượt trội, nhưng lại quá kiêu ngạo và thiếu khả năng kết nối xã hội. Cô ấy đang muốn lợi dụng tôi để làm cầu nối với những học sinh khác trong lớp.

"Được rồi," - Tôi thở dài một hơi nhẹ - "Tôi sẽ giúp cô trong khả năng của mình. Nhưng trước hết, chúng ta phải tìm ra quy luật chính xác của hệ thống S-Point này."
        """.trimIndent(),

        "Chương 4: Nhóm Học Tập Của Horikita" to """
Để cứu lấy điểm số cho bài kiểm tra giữa kỳ sắp tới - yếu tố quyết định trực tiếp đến điểm S-Point của lớp trong tháng 6 - Horikita quyết định tổ chức một nhóm học tập tại thư viện trường.

Đối tượng cần phải cứu vãn nhất chính là bộ ba học sinh yếu kém hàng đầu Lớp D: Sudō Ken, Ike Kanji và Yamauchi Haruki.

Sudō Ken là thành viên đội bóng rổ, sở hữu thể hình cao lớn và tính cách cực kỳ nóng nảy. Cậu ta đến thư viện với vẻ mặt cần nhằn: "Tại sao tôi phải tốn thời gian ngồi đây học mấy cái công thức Toán chết tiệc này chứ?! Tôi chỉ cần tập luyện bóng rổ để trở thành cầu thủ chuyên nghiệp là đủ rồi!"

Horikita thản nhiên gấp cuốn sách lại, nhìn Sudō với ánh mắt khinh bỉ: "Nếu cậu trượt bài kiểm tra giữa kỳ này, điểm số của cậu dưới mức chuẩn, cậu sẽ bị ĐUỔI HỌC ngay lập tức. Đến lúc đó, giấc mơ bóng rổ của cậu cũng sẽ tan thành mây khói."

"Cô nói cái gì cơ?!" - Sudō đập mạnh tay xuống bàn thư viện, làm mọi người xung quanh giật mình quay lại nhìn. "Cô khinh thường tôi đấy à?!"

"Tôi chỉ đang nói sự thật dựa trên quy chế nhà trường." - Tông giọng của Horikita vẫn lạnh như băng. "Nếu cậu không có đủ trí tuệ để vượt qua một bài thi cấp ba đơn giản, cậu đúng là một kẻ vô giá trị."

Căng thẳng leo leo dốc đến đỉnh điểm. Sudō túm lấy cổ áo Horikita, nắm đấm giơ lên sẵn sàng hạ xuống.

Tôi lẳng lặng bước tới, đặt tay lên cổ tay của Sudō. Bằng một lực bóp ẩn giấu ở huyệt đạo, tôi khiến tay Sudō tê dại và tự động thả cổ áo Horikita ra.

"Bớt nóng đi Sudō," - Tôi thì thầm - "Nơi này là thư viện. Nếu cậu gây nổ xô xát ở đây, camera giám sát trên tường sẽ ghi lại toàn bộ và Lớp D lại bị trừ thêm điểm đấy."

Sudō hằn học hất tay tôi ra, hốt đồ đạc vào cặp rồi hầm hầm bỏ đi: "Mệt kiếp! Tôi không học hành gì hết! Mặc kệ các người!"

Buổi học nhóm đầu tiên thất bại hoàn toàn. Horikita nhìn theo bóng lưng Sudō, đôi môi mỏng mím chặt đầy thất vọng.
        """.trimIndent(),

        "Chương 5: Sudō Ken Và Nguy Cơ Bị Đuổi Học" to """
Bài kiểm tra định kỳ giữa kỳ diễn ra trong sự căng thẳng tột độ của toàn bộ học sinh Lớp D.

Vài ngày trước kỳ thi, tôi đã âm thầm tiếp cận một học sinh khóa trên Lớp 3-D tại khu vực máy bán hàng tự động. Bằng cách bỏ ra 10.000 điểm cá nhân tích lũy từ tháng trước, tôi đã mua được toàn bộ bộ đề thi giữa kỳ của năm ngoái. 

Nhà trường Kōdo Ikusei tuy nghiêm khắc nhưng cấu trúc đề thi giữa kỳ hầu như không thay đổi qua các năm. Tôi trao bộ đề thi này cho Horikita để cô chia sẻ cho cả lớp dưới danh nghĩa "tài liệu ôn tập tự soạn".

Nhờ bộ đề thi đó, điểm số của Lớp D tăng vọt một cách kỳ diệu! Ike Kanji đạt 62 điểm, Yamauchi đạt 58 điểm. Cả lớp reo hò trong hạnh phúc.

Tuy nhiên, khi cô Chabashira dán bảng điểm lên bảng thông báo, một bầu không khí u ám đột ngột bao trùm.

Ở cuối danh sách, môn Điền Kinh của Sudō Ken ghi rõ:
- Điểm của Sudō: 38 điểm.
- Điểm chuẩn tối thiểu để qua môn: 39 điểm.

Sudō thiếu đúng 1 điểm duy nhất!

Cô Chabashira rút một chiếc bút đỏ ra, lạnh lùng gạch một đường gạch chéo màu đỏ tươi qua tên của Sudō Ken:

"Sudō Ken, em đã không đạt điểm chuẩn tối thiểu. Theo quy chế của nhà trường Kōdo Ikusei, em chính thức bị ĐUỔI HỌC từ ngày hôm nay. Hãy dọn dẹp đồ đạc trong ký túc xá và rời khỏi trường trước 18:00 chiều."

"Không... Không thể nào!" - Sudō rụng rời bàng hoàng, chiếc cặp sách trên tay rơi thịch xuống sàn nhà. Cậu gục xuống bàn, hai tay ôm lấy đầu trong sự tuyệt vọng vô bờ bến.
        """.trimIndent(),

        "Chương 6: Giao Dịch Bằng Điểm Số" to """
17:30 chiều. Hoàng hôn nhuộm đỏ rực cả hành lang khu nhà ban giám hiệu.

Tôi và Horikita Suzune đứng trước cửa phòng giáo viên. Horikita hít một hơi thật sâu rồi gõ cửa bước vào.

Cô Chabashira Sae đang ngồi một mình tại bàn làm việc, thong thả nhấp một ngụm cà phê nóng. Cô ngước mắt nhìn hai chúng tôi với nụ cười ẩn ý: "Các em đến đây để chia tay Sudō sao? Thời gian không còn nhiều đâu."

Horikita bước tới trước bàn làm việc, dằn hai tay xuống mặt bàn:
"Thưa cô Chabashira, chúng tôi muốn MUA 1 ĐIỂM môn Điền Kinh cho Sudō Ken!"

Cô Chabashira dừng ly cà phê lại trên không trung. Đôi mắt sắc lẹm của cô nheo lại: "Mua điểm? Trong quy chế chính thức của nhà trường chưa từng có khoản mục nào cho phép mua điểm thi cả."

"Nhưng cô đã từng nói trong buổi học đầu tiên," - Horikita dội ngược lại - "Rằng S-Point trong ngôi trường này có thể MUA ĐƯỢC BẤT CỨ THỨ GÌ. Từ vật phẩm, đồ ăn, cho đến quyền lợi học tập."

"Hahaha..." - Cô Chabashira bật cười khoái trá - "Khá lắm Horikita! Em đã nhận ra lỗ hổng tư duy đó. Đúng vậy, điểm số ở đây có thể mua được. Tuy nhiên, giá của 1 điểm thi giữa kỳ để cứu một học sinh khỏi án đuổi học không hề rẻ đâu."

"Giá của nó là bao nhiêu?" - Tôi cất tiếng hỏi.

"100.000 S-Point cho 1 điểm thi." - Cô Chabashira thản nhiên đáp.

Horikita tái mặt. Tài khoản của cô hiện tại chỉ còn khoảng 40.000 điểm sau các khoản chi tiêu bắt buộc.

"Tôi sẽ góp phần còn lại." - Tôi lấy chiếc điện thoại di động ra, mở ứng dụng chuyển điểm S-Point. "Tôi còn 60.000 điểm. Tổng cộng vừa đủ 100.000 điểm."

Cô Chabashira nhìn tôi chằm chằm bằng một ánh mắt vô cùng phức tạp và thâm thúy. Cô thong thả lấy chiếc bút đỏ, sửa điểm môn Điền Kinh của Sudō trên hệ thống từ 38 thành 39 điểm.

"Giao dịch hoàn tất. Sudō Ken chính thức được giữ lại trường. Hai em đúng là những học sinh thú vị nhất mà tôi từng chủ nhiệm..."
        """.trimIndent(),

        "Chương 7: Vụ Án Xô Xát Tại Hành Lang" to """
Sự việc Sudō thoát án đuổi học chưa kịp lắng xuống thì thảm họa mới lại ập đến với Lớp D.

Vào một buổi chiều mưa tầm tã, ba học sinh thuộc Lớp 1-C bao gồm Ishizaki, Komiya và Kondō xuất hiện tại phòng giáo vụ với những vết bầm tím trên mặt. Họ gửi đơn khiếu nại chính thức lên Hội Học Sinh và Ban Giám Hiệu:

"Chúng tôi bị Sudō Ken của Lớp D vô cớ vô cớ chặn đường hành hung và đánh đập dã man tại hành lang phòng tập thể thao!"

Nếu vụ việc này bị khép tội, Sudō Ken không chỉ bị đình chỉ thi đấu giải bóng rổ toàn quốc mà còn chắc chắn bị đuổi học vĩnh viễn vì vi phạm bạo lực học đường nghiêm trọng. Lớp D cũng sẽ bị trừ toàn bộ điểm kỷ luật trong tháng tiếp theo.

Sudō gào lên phân trần tại lớp học: "Tôi không hề vô cớ đánh họ! Là do ba tên đó cố tình khiêu khích, lăng mạ Lớp D và ra tay đánh tôi trước! Tôi chỉ tự vệ mà thôi!"

"Nhưng không có bất kỳ nhân chứng nào ở đó để chứng minh lời cậu nói là đúng cả," - Horikita thở dài khoanh tay - "Lớp C có 3 người cùng khai chứng chống lại cậu. Trong mắt nhà trường, lời khai của 3 người luôn có trọng lượng hơn 1 người."

Ryuuen Kakeru - thủ lĩnh ngầm tàn bạo của Lớp C - đứng ở ngoài cửa lớp 1-D, nhếch môi nở một nụ cười đắc thắng và ranh ma. Hắn cố tình đạo diễn vụ việc này nhằm triệt hạ lực lượng của Lớp D ngay từ vòng ngoài.
        """.trimIndent(),

        "Chương 8: Nhân Chứng Bí Mật Sakura Airi" to """
Để tìm kiếm chứng cứ giải oan cho Sudō, tôi và Horikita quyết định rà soát lại toàn bộ khu vực hành chính xung quanh phòng tập thể thao vào thời điểm xảy ra tai nạn.

Tại vị trí góc hành lang tầng 2, tôi phát hiện một chi tiết nhỏ: một camera giám sát an ninh bị hỏng từ lâu nhưng ngay góc đối diện có một cửa sổ mở hướng ra sân trường.

Trong khi điều tra, chúng tôi nhận ra cô bạn Sakura Airi - một học sinh cực kỳ nhút nhát, luôn đeo chiếc kính cận dày cộp và cúi gắm mặt trong Lớp D - đang cầm chiếc máy ảnh kỹ thuật số đắt tiền với vẻ mặt hoảng hốt.

Tôi bước lại gần Sakura, nhẹ nhàng hỏi: "Sakura-san, vào thời điểm 16:00 chiều thứ Ba, cậu đã ở gần phòng tập thể thao đúng không?"

Sakura giật mình lùi lại, hai tay ôm chặt chiếc máy ảnh vào ngực, đôi môi run rẩy: "Tớ... tớ không biết gì hết! Tớ không thấy gì cả!"

"Cậu không cần phải giấu," - Horikita cất tiếng - "Chiếc máy ảnh của cậu sở hữu ống kính góc rộng. Cậu đã vô tình chụp được khoảnh khắc ba học sinh Lớp C vây đánh Sudō trước đúng không?"

Sakura òa khóc nức nở. Cô thừa nhận mình có bức ảnh bằng chứng vô giá đó, nhưng cô không dám đứng ra làm nhân chứng trước Hội Học Sinh. 

Lý do là vì Sakura sở hữu một bí mật: ngoài đời cô là Gravure Idol nổi tiếng trên mạng với nghệ danh 'Shizuku'. Gần đây, cô đang bị một tên nhân viên biến thái tại cửa hàng điện tử trong trung tâm thương mại bám đuôi đe dọa. Cô sợ rằng nếu đứng ra trước ánh đèn sân khấu, bản dạng thật của cô sẽ bị phơi bày...
        """.trimIndent(),

        "Chương 9: Buổi Phân Xử Tại Hội Học Sinh" to """
9:00 sáng thứ Sáu. Buổi phân xử chính thức diễn ra tại phòng họp lớn của Hội Học Sinh.

Chủ trì buổi họp là Chủ tịch Hội Học Sinh - Horikita Manabu, anh trai của Horikita Suzune. Anh ta là một thiên tài toàn năng với ánh mắt lạnh như thép và phong thái uy nghiêm áp đảo toàn bộ phòng họp.

Bên phía Lớp C, thủ lĩnh Ryuuen Kakeru ngồi vắt chân tự tin, 3 học sinh bị thương liên tục đóng kịch than khóc vạch tạ sự tàn bạo của Sudō.

"Lớp D có nhân chứng hay bằng chứng nào phản bác lại không?" - Tông giọng của Manabu vang lên lạnh ngắt, gây áp lực tâm lý khủng khiếp lên Suzune.

Suzune run rẩy, giọng cô nghẹn lại trước sự uy nghiêm của anh trai mình. Cô quá sợ hãi ánh mắt của Manabu.

Tôi đứng ngay sau lưng Suzune, nhẹ nhàng dùng ngón tay ấn nhẹ vào lưng cô ấy và thì thầm vào tai: "Bình tĩnh lại, Horikita. Hãy nhìn vào bức ảnh. Cô là người duy nhất có thể cứu Lớp D lúc này."

Lời nói của tôi như một liều thuốc thức tỉnh. Suzune hít một hơi thật sâu, đôi mắt cô lấy lại sự sắc bén vốn có.

Cô dũng cảm bước lên, lấy chiếc USB ra kết nối với máy chiếu:

"Tôi xin trình bày BẰNG CHỨNG MỚI! Đây là bức ảnh do nhân chứng Sakura Airi chụp được vào đúng 16:02 chiều thứ Ba!"

Trên màn hình máy chiếu hiện ra bức ảnh cực kỳ rõ nét: Ishizaki và Komiya của Lớp C đang giữ chặt tay Sudō để Kondō đấm vào bụng cậu. Quá trình tự vệ của Sudō chỉ diễn ra sau đó!

Gương mặt Ryuuen Kakeru biến sắc hoàn toàn. Manabu đập mạnh tay xuống bàn: "Lớp C vi phạm tội khai man và vu khống! Vụ án chính thức khép lại!"
        """.trimIndent(),

        "Chương 10: Lời Cảnh Báo Của Ayanokōji" to """
Sau buổi phân xử thành công, Sakura Airi vui vẻ bước về ký túc xá. Nhưng thảm họa bám đuôi đã bùng nổ.

Tên nhân viên biến thái cửa hàng điện tử đã phát hiện ra địa chỉ ký túc xá của Sakura. Hắn chặn đường cô tại khu vực công viên vắng người sau giờ nghỉ, dồn Sakura vào góc tường với con dao trên tay: "Shizuku-chan... Em là của tôi! Tại sao em lại phản bội tôi để giúp đỡ đám người đó?!"

Sakura sợ hãi ngã khụy xuống đất, tiếng gào khóc cứu viện bị tiếng mưa rơi lấn áp.

Đúng lúc con dao của tên biến thái sắp hạ xuống, một bàn tay rắn như thép từ phía sau vươn ra, tóm chặt lấy cổ tay hắn!

Đó là tôi - Ayanokōji Kiyotaka.

Tôi siết chặt cổ tay tên biến thái khiến con dao rơi xoảng xuống mặt đường bê tông. Ánh mắt tôi nhìn hắn không một chút cảm xúc, lạnh lẽo như một cỗ máy giết người:

"Ngươi đã chọn sai đối tượng rồi đấy."

Bằng một cú đá tầm thấp cực kỳ chính xác vào khớp gối, tôi hạ gục tên biến thái trong chưa đầy 3 giây trước khi lực lượng an ninh của trường ập tới bắt giữ hắn.

Đêm đó, dưới ánh đèn đường hiu hắt của sân trường, cô giáo chủ nhiệm Chabashira Sae đứng đợi tôi.

Cô châm một điếu thuốc, nhả làn khói trắng vào không trung:

"Ayanokōji Kiyotaka... Em đã dàn xếp toàn bộ vụ việc mua điểm của Sudō, mua đề thi từ khóa trên, và khích lệ Horikita lật ngược thế cờ vụ án Lớp C đúng không?"

Tôi đáp với giọng điệu bình thản: "Cô đang nói gì vậy? Tôi chỉ là một học sinh bình thường đạt 50 điểm tất cả các môn thôi."

Cô Chabashira nhếch môi cười bí hiểm: "Đừng giấu tôi nữa. Người đàn ông đó - cha của em - đã liên hệ với nhà trường. Ông ấy yêu cầu trục xuất em về lại 'Căn Phòng Trắng' (White Room). Nếu em không giúp Lớp D vươn lên Lớp A, tôi sẽ chính thức ký vào đơn trục xuất em khỏi ngôi trường này."

Tôi đứng lặng yên dưới ánh trăng đêm. Cuộc sống bình yên mà tôi tìm kiếm có vẻ như đã chính thức khép lại...
        """.trimIndent()
    )
}
