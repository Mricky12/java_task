<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import ="java.util.List" %>    
<%@ page import="task.dto.GroupsDTO" %>    
    
<!DOCTYPE html>
<html lang="ja">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>タスク管理システム</title>
    <link href="./css/reset.css" rel="stylesheet" type="text/css"/>
    <link href="./css/style.css" rel="stylesheet" type="text/css"/>
    <link href="./css/main.css" rel="stylesheet" type="text/css"/>
</head>
<body>
    <div class="main-wrapper">
        <header class="main-header">
            <div class="logo">
                <!-- ハンバーガーメニューアイコン -->
                <div class="hamburger" id="hamburger">
                    <span></span>
                    <span></span>
                    <span></span>
                </div>
            </div>
            <div class="header-title">
                <p>
                    タスク管理システム
                    <br>グループページ
                </p>
            </div>
            
            <div class="search">
                <form action="/search" method="get" class="search-form-4">
                    <button type="submit" aria-label="検索"></button>
                    <label>
                        <input type="text"  placeholder="検索" >
                    </label>
                </form>
            </div>
            <div class="icon-container">
                <a href="" class="icon-link">
                    <img src="images\リロードのフリーアイコン.png" alt="リロード">
                </a>
                <a href="" class="icon-link">
                    <img src="images\narabikae.png" alt="リスト表示">
                </a>
                <a href="" class="icon-link">
                    <img src="images\無料の設定歯車アイコン.png" alt="設定">
                </a>
            </div>
            <div class="icon-container">
                <a href="" class="icon-link">
                    <img src="images\メニューの無料アイコン4.png" alt="ホーム">
                </a>
                <a href="./edituser.jsp" class="icon-link">
                    <img src="images\人物のアイコン.form素材 その3.png" alt="アカウント">
                </a>
            </div>
        </header> 
        <div class="container">
            <div class="sidebar">
                <ul class="menu">
                    <li><a href="myselftask.jsp"><span class="bullet">・</span>マイタスク</a></li>
                    <li><a href="groupcreate.jsp"><span class="bullet">・</span>グループ作成/編集</a></li>
                    <li><a href=""><span class="bullet">・</span>グループメンバー編集</a></li>
                    <li><a href="grouptask.jsp"><span class="bullet">・</span>グループタスク一覧</a></li>
                    <li><a href="edituser.jsp"><span class="bullet">・</span>ユーザー編集</a></li>
                    <li><a href="#logout" id="logout-link"><span class="bullet">・</span>ログアウト</a></li>
                </ul>
            </div>
            <div class="main-content">
                <p>グループメンバー削除</p>
                <div class="main-content-child">
                    <!-- グループ選択 -->
					<div class="select-container">
  						<select class="custom-select" name="custom-select" id="group-select">
    					<option value="" selected>▼グループ選択</option>
    					<option value="group1">グループ1</option>
    					<option value="group2">グループ2</option>
    					<option value="group3">グループ3</option>
  						</select>
					</div>
                    
                </div>
                <div class="button-container">
                    <button class="cancel-button" type="button">キャンセル</button>
                    <button class="bule-button search-button" type="button">追加</button>
                </div>
            </div>
            <div class="main-content">
                <p>グループメンバー追加</p>
                <form action="edit" method="post" class="main-content-child">
                    <!-- グループ選択 -->
					<div class="select-container">
  						<!-- グループリストを動的に表示 -->
            			<select name="groupId" class="group-select">
                    		<option value="" selected>▼グループ選択</option>
                    		<%
                    		// グループリストをリクエストから取得
                   			List<GroupsDTO> groups = (List<GroupsDTO>) request.getAttribute("groups");
                    		if (groups != null && !groups.isEmpty()) {
                        		for (GroupsDTO group : groups) {
                        			// デバッグ出力
                                    System.out.println("JSPで取得したグループID: " + group.getGroupId() + ", グループ名: " + group.getGroupName());

                    		%>
                        		<option value="<%= group.getGroupId() %>"><%= group.getGroupName() %></option>
                    		<%
                        		}
                    		} else {
                   	 		%>
                        		<option value="">グループがありません</option>
                    		<%
                    		}
                    		%>
                		</select>
					</div>
                    <div class="form-group">
                        <label for="name">名前<span class="small-text">または</span>メールアドレス</label>
                        <form id="searchForm"  class="search-form-5">
                            <button type="submit" aria-label="検索"></button>
                            <label>
                                <input type="text" id="searchInput" name="query"class="form-control" placeholder="">
                            </label>
                        </form>    
                    </div>
                    <div class="button-container">
                    	<button class="cancel-button" type="button">キャンセル</button>
                    	<button class="search-button" type="button">追加</button>
                	</div>
                </form>
                
                
            </div>
            <div class="result-content">
                <!-- 検索結果の表示 -->
                <div id="searchResults" class="result-content"></div>
            </div>

        </div>    
        
    </div>
    <script src="./js/script.js"></script>
    <script src="./js/group_create.js" defer></script>
</body>
</html>