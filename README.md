# 財布の小銭最適化

## プロジェクトの内容

### 概要

自分のお財布の中が常に最適な状態になるよう手助けをしてくれるAndroidアプリです。

```
Ex)
- 手持ち: ￥113
- お会計: ￥108

- お支払い: ￥110, ￥113
- おつり: ￥2😕, ￥5😍
```

### プロジェクト用に作成した各ファイルの内容とその機能

- MainActivity.java
    > アプリ画面の処理を記述しています。


- activity_main.xml
    > オブジェクトの配置を定義しています。


- colors.xml
    > 使用する色を定義しています。


- strings.xml
    > 使用する定数を定義しています。


### 注意事項
財布に無駄な小銭がある場合は不適切な計算結果になります。1円、10円、100円硬貨は最大4枚、5円、50円、500円硬貨は最大1枚の環境でのみ正しく動作します。


<img src="https://user-images.githubusercontent.com/80265368/155042111-cdfb59aa-e97b-41b3-94e2-b3d993fd1e1c.png" width="320px">



### 動作確認済み環境(Tested environment)

    - Android 5
    - Android 7
    - Android 9

## 参考文献(References)

- [財布の小銭の最適化その2](http://takeno.iee.niit.ac.jp/~shige/math/lecture/misc/data/kozeni2.pdf)
