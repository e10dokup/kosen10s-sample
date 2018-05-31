# 構成

Dagger/RxレスMVPを自称しています。

大体以下の構成

* example
    * datasource
    * entity
    * presenter
    * service
    * view
        * <screen>
        * item
        * navigator
    * MainApplication.kt

以下それぞれなにをしているのか

# datasource

データの取得についてのクラスをまとめているぞ！APIにアクセスしたり、Firebaseにアクセスしたり、DBからデータを取ってくる処理はここに書こう！

# entity

データ構造についてのクラスをまとめているぞ！moshi/kotshiっていうパーサを使っているので `@JsonSerialize` っていうアノテーションをつけるとJsonパーサがドンって感じで楽だぞ！

# service

RetrofitがAPI連携するためのインターフェースを書くところだぞ！引数を `@Query` アノテーション付きで書くとクエリができたり、 `@Path` アノテーション付きで書くとパス変数ができるぞ！

# view/<screen>

画面についてのActivity/Fragmentはここに入れるぞ！CustomViewについては完全に考えていていなかったぞ！

# item

Groupieでリスト要素の中身になるデータ構造とリストの要素のヒモ付を行うクラスをまとめているぞ！

# navigator

画面遷移を行うクラスをまとめているぞ！

## なんでこんなことしちゃったの！？

ホントは設計したくなかったけど複数人で書くとコンフリクトで消耗する未来が見えたんだ！許してヒヤシンス！