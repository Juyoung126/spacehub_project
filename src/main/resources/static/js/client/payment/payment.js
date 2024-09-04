$(document).ready(function() {
	    $('.payment-button').click(async function() {
	        var paymentMethod = $(this).data('method');
	        console.log("Selected payment method: " + paymentMethod);

	        // 기본값 설정
	        let channelKey = "";
	        let payMethod = "";

	        // 결제 방법에 따라 키 값 설정
	        if(paymentMethod === "kakao") {
	            channelKey = "channel-key-1972b134-d930-4897-9599-f805035dac66";
	            payMethod = "EASY_PAY";
	        } else if (paymentMethod === "toss") {
	            channelKey = "channel-key-31017ccc-371d-440c-b9e4-459bc3323d14"; // 실제 토스 채널 키로 변경
	            payMethod = "EASY_PAY";
	        } else if (paymentMethod === "basic") {
	            channelKey = "channel-key-f20734f6-5df3-406f-8d66-56a0ffa0aa1f"; // 실제 일반 결제 채널 키로 변경
	            payMethod = "CARD";
	        } else if (paymentMethod === "mobile") {
	            channelKey = "channel-key-f20734f6-5df3-406f-8d66-56a0ffa0aa1f"; // 실제 일반 결제 채널 키로 변경
	            payMethod = "MOBILE";
	        }

	        try {
	            const paymentId = `pay-${crypto.randomUUID().substring(0, 20)}`; // 주문 번호를 40자 이하로 제한

	            const requestOptions = {
	                storeId: "store-6fcc723b-ff52-4915-9fdd-d5bc06e8c273",
	                channelKey: channelKey,
	                paymentId: paymentId,
	                orderName: "1번방",
	                totalAmount: 100000,
	                currency: "KRW",
	                payMethod: payMethod,
	                
	            };

	            // 결제 방법이 모바일일 경우 추가 옵션 설정
	            if(paymentMethod === "mobile"){
	                requestOptions.bypass = {
	                    "kcp_v2": {
	                        "site_logo": "https://portone.io/assets/portone.jpg",
	                        "skin_indx": 6,
	                        "shop_user_id": "user_id1",
	                        "site_name": "모바일 결제"
	                    }
	                };
	            }

	            // 결제 요청 함수 호출
	            const response = await PortOne.requestPayment(requestOptions);

	            if (response.code != null) {
	                // 오류 발생 시 처리
	                return alert(response.message);
	            }
	            
	            const tid = response.tid;

	            // 결제가 성공하면 서버로 결제 완료 정보 전송
	            const SERVER_BASE_URL = "http://localhost:8080";
	            const notified = await fetch(`${SERVER_BASE_URL}/payment/completeCheck`, {
	                method: "POST",
	                headers: { "Content-Type": "application/json" },
	                body: JSON.stringify({
	                    paymentId: paymentId,
	                    tid: tid,
	                    orderName: requestOptions.orderName,
	                    totalAmount: requestOptions.totalAmount,
	                    payMethod: payMethod,
	                    res_no: 12,
	                }),
	            });

	            if (notified.ok) {
	                // 결제 성공 시 페이지 이동
	                window.location.href = "/payment/complete";  // 결제 성공 페이지로 이동
	            } else {
	            	
	                alert(await notified.text());
	            }

	        } catch (error) {
	            console.error("결제 요청 중 오류가 발생했습니다:", error);
	            alert("결제 요청 중 오류가 발생했습니다.");
	        }
	    });

	    
	});
