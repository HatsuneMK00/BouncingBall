# OOAD LAB3项目

## 小组分工

- 郭省吾： 待定
- 罗竣夫： 文件系统/引擎部分
- 袁非凡： 文件系统/UI部分

## 说明

- 现在的MainGame类可以跑，会出来一个页面和一个简单的UI控件。
- src/resources/view中放fxml文件，由于如果fxml文件和它的Controller不放在一起的话，会无法进行数据的绑定，因此先放一块，绑完之后再丢到view里面去。
- model目录是给文件系统准备的，和游戏和布局模式都没有关系。
- 剩下的看注释就行

## 可能出现的错误

- 发行版本不正确：去settings里面的compile里面的java compilor中把版本设置为11，在project structure中把language level设置为11