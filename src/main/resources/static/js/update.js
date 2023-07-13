// (1) 회원정보 수정
/**
 * ajax로 회원 정보 수정 요청
 * @param userId
 * @param event
 */
function update(userId, event) {
    event.preventDefault(); // 폼태그 액션 막기
    let data = $("#profileUpdate").serialize();

    console.log(data);

    $.ajax({
        type: "put",
        url: `/api/user/${userId}`,
        data: data,
        contentType: "application/x-www-form-urlencoded; charset=utf-8",
        dataType: "json"
    }).done(res=>{
        console.log("update 성공", res);
        location.href = '/';
        // location.href = `/user/${userId}`;
    }).fail(error=>{
        alert(error.responseJSON.message);
        // const errors = error.responseJSON.data;
        // if(Object.keys(errors).length>0){
        //     Object.entries(errors).forEach(
        //         ([key, value]) => alert(`${key} : ${value}`)
        //     );
        // }
    });

}