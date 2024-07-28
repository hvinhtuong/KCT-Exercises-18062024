                                ----------------------------------WAREHOUSE MANAGER-----------------------------------

- Admin có quyền CRUD user, warehouse, xem sanh sách sản phẩm

- Admin có quyền thêm kho, chọn kho để xem sửa xóa

- Kho ko có user thì chưa được thêm sản phẩm

- Mỗi user chỉ được xem thông tin kho của mình

- User nhập sản phẩm từ excel, chỉ được nhập kho của mình

- Create User/Warehouse: có thể gán kho cho user sau và ngược lại

- Xóa User: Xóa kho trước, xóa user sau

- Xóa kho:

+ Kiểm tra kho có sản phẩm ko, nếu ko xóa bth, nếu có -> Chuyển sp qua kho khác có user rồi mới xóa kho.

- User xuất báo cáo thống kê tổng số lượng sp trong mỗi kho ra excel

- Admin xuất báo cáo thống kê tổng SL sp trong toàn kho ra excel

![ERD](https://github.com/user-attachments/assets/9726d112-f51d-48bf-a6ac-c996e0db7f2e)
