package br.com.api.sistema.service;

import br.com.api.sistema.entity.AbstractEntity;
import br.com.api.sistema.exception.NotFoundException;
import br.com.api.sistema.repository.AbstractRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

/**
 * Classe abstrata de service onde tem os principais métodos a serem usados
 * @param <T> Entidade
 */
public abstract class AbstractService <T extends AbstractEntity> {

    protected final AbstractRepository<T> repository;

    protected AbstractService(AbstractRepository<T> repository) {
        this.repository = repository;
    }

    /**
     * Método para setar atributos atualizados
     * @param objeto objeto atual
     * @param objetoAtualizado objeto com valores atualizados
     */
    protected void setarAtributosObjeto(T objeto, T objetoAtualizado) {
    }

    /**
     * Método que retorna nome da entidade
     * @return nome do Objeto
     */
    protected abstract String getMensagem();

    /**
     * Método para buscar todos os objetos
     * @return lista de objeto
     */
    public List<T> obterTodos() {
        return this.repository.findAll();
    }

    /**
     * Método para criar objeto
     * @param objeto novo objeto
     * @return Objeto ou BadRequestException
     */
    public T criarObjeto(T objeto) {

        objeto.setCriado(LocalDate.now());
        objeto.setModificado(LocalDate.now());

        return this.repository.save(objeto);
    }

    /**
     * Método para buscar o objeto pelo Id
     * @param id identificador do objeto
     * @return Objeto ou NotFoundException
     */
    public T obterObjetoPorId(Long id) {
        Optional<T> objeto = this.repository.findById(id);
        return objeto.orElseThrow(() -> new NotFoundException(this.getMensagem() + " de id:" + id + " não foi encontrado(a)!"));
    }

    /**
     * Método para atualizar objeto
     * @param id identificador do objeto
     * @param objetoAtualizado objeto atualizado
     * @return Objeto ou NotFoundException
     */
    public T atualizarObjeto(Long id, T objetoAtualizado) {
        T objeto = this.obterObjetoPorId(id);

        this.setarAtributosObjeto(objeto, objetoAtualizado);

        objeto.setNome(objetoAtualizado.getNome());
        objeto.setModificado(LocalDate.now());

        return this.repository.save(objeto);
    }

    /**
     * Método para deletar objeto
     * @param id identificador do objeto
     */
    public void deletarObjeto(Long id) {
        this.obterObjetoPorId(id);
        this.repository.deleteById(id);
    }
}
