# 環境削除方法

- `t-<tenantid>-saasus-setup-private-link-on-saas` の cloudformation のリソースから 作成した vpc endpoint service id を取得する
- `reject_vpc_endpoints.sh <vpc endpoint service id>` で vpc endpoint service id のエンドポイント接続を reject する（これを行わないと cloudformation の削除がエラーになります）
- `t-<tenantid>-saasus-setup-private-link-on-saas` の cloudformation を削除する
- saas sample の cloudformation を削除する
