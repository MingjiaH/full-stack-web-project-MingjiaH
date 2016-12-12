<!DOCTYPE html>
<html lang="en">
<head>
  <#include "header.ftl">
</head>
<body>
  <div class="topTitle">
    <div class="logo"><img src="img/logo.png" alt=""/></div>
    <div id="loginInfo" style="display:none;">
    	<div class="UserInfo" id="info-message">
      		<h5>placeholder</h5>
      		<a href="" id="signOut" class="signOut">Sign Out</a>
    	</div>
	</div>
    <div id="loginBtn">
      <div class="sign">
    	<div class="signIn"><a href="signUp.html">SignIn</a></div>
    	<div class="signUp"><a href="signUp.html">Register</a></div>
      </div>
    </div>
  </div>
  <div class="background">
  </div>
  <div class="navigator">
    <ul class="nav nav-tabs">
	<li><a href="homepage.html">Home</a></li>
	<li class="active"><a href="#">News</a></li>
	<li><a href="vote.html">Voting</a></li>
	<li><a href="calendar.html">Calendar</a></li>
    </ul>
  </div>
  <div class="content">
    <div class="row clearfix">
		<div class="col-md-8 column">
			<#list newsCollection as news>
			<#if news??>
			<div class="first">
				<div class="page-header">
					<h1>
						${news.firstTitle} <small>${news.secondTitle}</small>
					</h1>
				</div><img alt="140x140" src=${news.picUrl} class="news-image" />
				<p>
					${news.content}
				</p> <span class="label label-primary">${news.firstTopic}</span>
				<span class="label label-primary">${news.secondTopic}</span>
				<span class="label label-primary">${news.thirdTopic}</span>
			</div>
			</#if>
			</#list>  
			<div class="pages">
			  <ul class="pagination">
	          <li><a href="#">&laquo;</a></li>
	          <li class="active"><a href="#">1</a></li>
	          <li><a href="#">2</a></li>
	          <li><a href="#">3</a></li>
              <li><a href="#">4</a></li>
              <li><a href="#">5</a></li>
	          <li><a href="#">&raquo;</a></li>
              </ul>
			</div>
		</div>
		<div class="col-md-4 column">
		    <h4>Latest News</h4>
		    <div class="NewsList">
			<ol>
				<li>
					IOI final stage
				</li>
				<li>
					Mama 2016
				</li>
				<li>
					Pink Revolution 
				</li>
				<li>
					Facilisis in pretium nisl aliquet
				</li>
				<li>
					Nulla volutpat aliquam velit
				</li>
				<li>
					Faucibus porta lacus fringilla vel
				</li>
				<li>
					Aenean sit amet erat nunc
				</li>
				<li>
					Eget porttitor lorem
				</li>
			</ol>
			</div>
		</div>
	</div>  
  </div>

</body>
</html>