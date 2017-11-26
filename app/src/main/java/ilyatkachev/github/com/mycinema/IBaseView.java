package ilyatkachev.github.com.mycinema;

public interface IBaseView<T extends IBasePresenter> {

    void setPresenter(T pPresenter);
}
