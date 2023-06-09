<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %> <%@ page
language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE HTML>
<html>
  <head>
    <meta charset="UTF-8" />
    <script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
    <script
      type="text/javascript"
      src="//cdnjs.cloudflare.com/ajax/libs/jqueryui/1.10.4/jquery-ui.min.js"
    ></script>
    <script src="//netdna.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
    <link
      href="//netdna.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css"
      rel="stylesheet"
      id="bootstrap-css"
    />
    <title>todoList</title>
  </head>
<script type="text/javascript">
	function check() {
		//if(document.searchFrm.keyWord.value==""){
		//	alert("검색어를 입력하세요.");
		//	document.searchFrm.keyWord.focus();
		//	return;
		//}
		document.searchFrm.submit();
	}
	function changeCategory(category)
	{
		document.readFrm.category.value=category;
		document.readFrm.submit();
	}
	function pageing(page) {
		document.readFrm.nowPage.value=page;  
		document.readFrm.submit();
	}
	function numPerFn(numPerPage) {
		document.readFrm.action="boardPage.jsp";
		document.readFrm.numPerPage.value=numPerPage;
		document.readFrm.submit();
	}
	function block(block) {
		document.readFrm.nowPage.value=
		<%=pagePerBlock%>*(block-1)+1;   
		document.readFrm.submit();
	}
	function read(num)
	{
		document.readFrm.num.value=num;
		document.readFrm.action="reviewPage.jsp";
		document.readFrm.submit();
	}
</script>
<body bgcolor="#FFFFFF">
    <div class="container"  >
        <div class="row">
            <div class="col-4" style="background-color: aqua; padding: 0px;">
                <img   class="img-fluid" id="main_logo" src="img/logo1.png" alt="img" >  
            
            </div>
            <div class="col-6" style=" padding: 0px;">
            <form  role="search" >
                    <input  class="form-control me-2" id="main_search" type="search" placeholder="Search" aria-label="Search">   

            </div>
            <div class="col-1" style=" padding: 0px;">
            
                <button  class="btn btn-outline-success" id="search_btn" type="submit" >검색</button>
            </div>
            </form>
            <div class="col-2" style=" padding: 0px;">
            <%if(loginedid == null){ %>
              <button type="button" class="btn" onclick="javascript:location.href='/movieProject/user/login.jsp'" >로그인</button>
              <button type="button" class="btn" onclick="javascript:location.href='/movieProject/user/memberJoin.jsp'">회원가입</button>
           <%}else {%>
              <button type="button" class="btn" onclick="javascript:location.href='/movieProject/user/myPage1.jsp'" ><%=userNm %></button>
              <button type="button" class="btn" onclick="javascript:location.href='/movieProject/logoutServlet'">로그아웃</button>
           <%} %> 
            </div>

        </div>
        <div class="row" style=" padding: 5px;">
        </div>
    
        <div class="row">
          <nav>
            <ul>
              <li><a class="navTop" href="/movieProject/user/mainpage1.jsp">홈</a></li>
              <li><a class="navTop" href="/movieProject/movie/rankingPage.jsp">랭킹</a></li>
              <li><a class="navTop" href="/movieProject/movie/movieList.jsp">영화</a></li>
              <li><a class="navTop" href="/movieProject/movie/recommendPage.jsp">추천</a></li>
              <li><a class="navTop" href="/movieProject/board/agoraBoardPage.jsp">토론</a></li>
              <li><a class="navTop" href="/movieProject/board/boardPage.jsp">게시판</a></li>
              <li><a class="navTop" href="/movieProject/board/qnaBoardPage.jsp">문의</a></li>
            </ul>
          </nav>
        </div>
<div align="center"><br/>
<h2><%if(category.equals("0")){ %>전체<%} else { %><%=category %><%} %> 게시판 - 무비어때</h2>
<table>
	<tr>
		<td width="600">
		총 게시글 수 : <%=totalRecord%> 개(<font color="red">
		<%=nowPage+"/"+totalPage%>쪽</font>)
		</td>
		<td align="right">
			<form name="npFrm" method="post">
				<select name="numPerPage" size="1" 
				onchange="javascript:numPerFn(this.form.numPerPage.value)">
    				<option value="5">5개 보기</option>
    				<option value="10" selected>10개 보기</option>
    				<option value="15">15개 보기</option>
    				<option value="30">30개 보기</option>
   				</select>
   				<script>document.npFrm.numPerPage.value=<%=numPerPage%></script>
   			</form>
		</td>
		<td align="right">
			<form name="catFrm" method="post">
				<select name="category" size="1" onchange="javascript:changeCategory(this.form.category.value)">
					<option value="0">카테고리</option>
					<option value="리뷰">리뷰</option>
					<option value="추천">추천</option>
					<option value="일반">일반</option>
					<option value="0">전체</option>
				</select>
				<script>document.catFrm.category.value=<%=category%></script>
			</form>
		</td>
	</tr>
</table>
<table>
	<tr> 
		<td align="center" colspan="2">
		<%
				Vector<BoardBean> vlist =mgr.getBoardList(category, keyField, keyWord, movieIdx);
				int listSize = vlist.size();
				if(vlist.isEmpty()){
					out.println(" 등록된 게시물이 없습니다.");
				}else{
		%>
			<table cellspacing="0">
				<tr align="center" bgcolor="#D0D0D0">
					<td width="100">번 호</td>
					<td width="250">제 목</td>
					<td width="100">작성자</td>
					<td width="150">작성일</td>
					<td width="100">좋아요</td>
				</tr>	
				<%
					for(int i=0;i<numPerPage/*10*/;i++){
						if(totalRecord-start-i<=0) break;
						//if(i==listSize) break;
						BoardBean bean = vlist.get(totalRecord-start-i-1);
						int num = bean.getBoardIdx();
						String subject = "<"+bean.getCategory()+">"+bean.getTitle();
						String name=mmgr.getMember(bean.getUserId()).getUsernn();
						//String name = "dummy";
						String regdate = bean.getPostedDate().toString();
						//int depth = bean.getDepth();
						//int count = bean.getCount();
						//String filename = bean.getFilename();
						int ccount=mgr.getCommentCount(num);
						int lcount=bean.getLiked();
				%>
				<tr align="center">
					<td><%=totalRecord-start-i%></td>
					<td align="left">
					<%//for(int j=0;j<depth;j++){out.println("&nbsp;&nbsp;");} %>
					<a href="javascript:read('<%=num%>')">
					<%=subject%></a>
					<!-- <%//if(filename!=null&&!filename.equals("")){ %>
						<img alt="첨부파일" src="img/icon.gif" align="middle">	
					<%//}%> -->
					<%if(ccount>0){ %>
					<font color="black">[<%=ccount %>]</font>
					<%} %>
					</td>
					<td><%=name%></td>
					<td><%=regdate%></td>
					<td><%=lcount%></td>
				</tr>
				<%}//--for	%>
			</table>
		<%}//--if-else%>	
		</td>
	</tr>
	<tr>
		<td colspan="2"><br><br></td>
	</tr>
	<tr>
		<td>
		<!-- 페이징 및 블럭 Start -->
		<!-- 이전블럭 -->
		<%if(nowBlock>1){ %>
			<a href="javascript:block('<%=nowBlock-1%>')">prev...</a>
		<%}%>	
		<!-- 페이징 -->
		<%
				int pageStart = (nowBlock-1)*pagePerBlock+1;
				int pageEnd = (pageStart+pagePerBlock)<totalPage?
						pageStart+pagePerBlock:totalPage+1;
				for(;pageStart<pageEnd;pageStart++){
		%>
		<a href="javascript:pageing('<%=pageStart%>')">
		<%if(nowPage==pageStart){%><font color="blue"><%}%>
			[<%=pageStart%>]
		<%if(nowPage==pageStart){%></font><%}%>
		</a>
		<%}//--for%>
		<!-- 다음블럭 -->
		<%if(totalBlock>nowBlock){ %>
			<a href="javascript:block('<%=nowBlock+1%>')">...next</a>
		<%}%>
		<!-- 페이징 및 블럭 End -->
		</td>
		<td align="right">
		<%if(isLogined){ %>
			<a href="selectMovieForBoard.jsp">[글쓰기]</a>
			<%} else { %>
			<a href="/movieProject/user/login.jsp">[글쓰기]</a>
			<%} %>
			<a href="boardPage.jsp">[처음으로]</a>
		</td>
	</tr>
</table>

<hr width="750">
<form  name="searchFrm">
	<table  width="600" cellpadding="4" cellspacing="0">
 		<tr>
  			<td align="center" valign="bottom">
   				<select name="keyField" size="1" >
    				<option value="USERID"> 이 름</option>
    				<option value="TITLE"> 제 목</option>
    				<option value="DETAIL"> 내 용</option>
   				</select>
   				<input size="16" name="keyWord">
   				<input type="button"  value="찾기" onClick="javascript:check()">
   				<input type="hidden" name="nowPage" value="1">
  			</td>
 		</tr>
	</table>
</form>

<form name="listFrm" method="post">
	<input type="hidden" name="reload" value="true">
	<input type="hidden" name="nowPage" value="1">
</form>

<form name="readFrm">
	<input type="hidden" name="nowPage" value="<%=nowPage%>">
	<input type="hidden" name="numPerPage" value="<%=numPerPage%>">
	<input type="hidden" name="keyField" value="<%=keyField%>">
	<input type="hidden" name="keyWord" value="<%=keyWord%>">
	<input type="hidden" name="category" value="<%=category %>">
	<input type="hidden" name="num">
</form>
</div>
</body>
</html>