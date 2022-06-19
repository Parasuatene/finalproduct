package dataaccess.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dataaccess.value.Book;
import dataaccess.value.RentalControl;

public class BookDAO extends BaseDAO {

	/**
	 * コンストラクタ
	 * @param connection
	 */
	public BookDAO(Connection connection) {
		super(connection);
	}

	/**
	 * Bookテーブルから全てのデータを取得する
	 * @return bookテーブル内の全てのデータ
	 */
	public List<Book> selectAll() {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
//			// SQLの定義
//			String sql = "SELECT tb.id, tb.title, tb.author, tb.publisher, tb.img_path, tb.discription"
//						 + ", vr.id, vr.user_id, vr.book_id, vr.start_date, vr.schedule_date, vr.end_date "
//						 + "FROM t_book AS tb "
//						 + "LEFT OUTER JOIN v_latest_rental_info AS vr "
//						 + "ON tb.id = vr.book_id";
			// SQLの定義
			String sql = "SELECT tb.id AS tb_id, tb.title AS tb_title, tb.author AS tb_author"
						 + ", tb.publisher AS tb_publisher, tb.img_path AS tb_img_path, tb.discription AS tb_discription"
						 + ", vr.id AS vr_id, vr.user_id AS vr_user_id, vr.book_id AS vr_book_id"
						 + ", vr.start_date AS vr_start_date, vr.schedule_date AS vr_schedule_date, vr.end_date AS vr_end_date "
						 + "FROM t_book AS tb "
						 + "LEFT OUTER JOIN v_latest_rental_info AS vr "
						 + "ON tb.id = vr.book_id";
			// SQLの作成
			preparedStatement = getConnection().prepareStatement(sql);
			// SQLの実行
			resultSet = preparedStatement.executeQuery();
			// 結果を格納するためのリスト
			List<Book> bookList = new ArrayList<Book>();
			while(resultSet.next()) {
				Book book = new Book();
				book.setId(resultSet.getInt("tb_id"));
				book.setTitle(resultSet.getString("tb_title"));
				book.setAuthor(resultSet.getString("tb_author"));
				book.setPublisher(resultSet.getString("tb_publisher"));
				book.setImgPath(resultSet.getString("tb_img_path"));
				book.setDiscription(resultSet.getString("tb_discription"));

				RentalControl rentalControl = new RentalControl();
				rentalControl.setId(resultSet.getInt("vr_id"));  // TODO: 貸出管理番号が取得できるようにする
				rentalControl.setUserId(resultSet.getString("vr_user_id"));
				rentalControl.setBookId(resultSet.getInt("vr_book_id"));
				rentalControl.setStartDate(resultSet.getDate("vr_start_date"));
				rentalControl.setScheduleDate(resultSet.getDate("vr_schedule_date"));
				rentalControl.setEndDate(resultSet.getDate("vr_end_date"));
				book.setRentalControl(rentalControl);

				bookList.add(book);
			}
			return bookList;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			try {
				if (resultSet != null) {
					resultSet.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
			try {
				if (preparedStatement != null) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				throw new RuntimeException(e);
			}
		}
	}

	/**
	 * 指定したIDに該当する書籍情報を取得する
	 * @param argId
	 * @return IDに該当する書籍。または該当しない場合はnullを返す。
	 */
	public Book selectById(int argId) {
		PreparedStatement preparedStatement = null;
		ResultSet resultSet = null;
		try {
			// SQLの定義
			String sql = "SELECT id, title, author, publisher, img_path, discription "
					 	 + "FROM t_book "
					 	 + "WHERE id = ?";
			// SQLの作成
			preparedStatement = getConnection().prepareStatement(sql);
			// 値の設定
			preparedStatement.setInt(1, argId);
			// SQLの実行
			resultSet = preparedStatement.executeQuery();

			Book book = null;
			// 問い合わせ結果の取得
			while(resultSet.next()) {
				book = new Book();
				book.setId(resultSet.getInt("id"));
				book.setTitle(resultSet.getString("title"));
				book.setAuthor(resultSet.getString("author"));
				book.setPublisher(resultSet.getString("publisher"));
				book.setImgPath(resultSet.getString("img_path"));
				book.setDiscription(resultSet.getString("discription"));
			}
			return book;
		} catch (SQLException e) {
			throw new RuntimeException(e);
		} finally {
			if (preparedStatement != null) {
				try {
					preparedStatement.close();
				} catch (SQLException e) {
					throw new RuntimeException(e);
				}
			}
		}
	}
}