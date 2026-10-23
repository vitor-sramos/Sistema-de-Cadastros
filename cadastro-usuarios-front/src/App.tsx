import { useCallback, useEffect, useMemo, useState, type FormEvent } from 'react'
import { Activity, AlertCircle, ArrowDownToLine, Check, ChevronLeft, ChevronRight, CirclePlus, Cloud, Database, Pencil, RefreshCw, Search, ShieldCheck, Trash2, Users, X } from 'lucide-react'

type Usuario = { id: string; usuario: string; email: string }
type UsuarioForm = { usuario: string; email: string; senha: string }

const API_URL = (import.meta.env.VITE_API_URL || 'http://44.199.207.150:8080').replace(/\/$/, '')
const emptyForm: UsuarioForm = { usuario: '', email: '', senha: '' }

async function api<T>(path: string, options: RequestInit = {}): Promise<T> {
  const response = await fetch(`${API_URL}${path}`, {
    ...options,
    headers: { ...(options.body ? { 'Content-Type': 'application/json' } : {}), ...options.headers },
  })
  if (!response.ok) {
    let message = `Erro HTTP ${response.status}`
    try {
      const data = await response.json()
      message = data.message || data.mensagem || data.error || message
    } catch {
      try { message = (await response.text()) || message } catch { /* resposta sem corpo */ }
    }
    throw new Error(message)
  }
  if (response.status === 204) return undefined as T
  const text = await response.text()
  return (text ? JSON.parse(text) : undefined) as T
}

function App() {
  const [usuarios, setUsuarios] = useState<Usuario[]>([])
  const [loading, setLoading] = useState(true)
  const [error, setError] = useState('')
  const [notice, setNotice] = useState('')
  const [search, setSearch] = useState('')
  const [modalOpen, setModalOpen] = useState(false)
  const [editing, setEditing] = useState<Usuario | null>(null)
  const [form, setForm] = useState<UsuarioForm>(emptyForm)
  const [saving, setSaving] = useState(false)
  const [deletingId, setDeletingId] = useState<string | null>(null)

  const loadUsuarios = useCallback(async () => {
    setLoading(true)
    setError('')
    try {
      const data = await api<Usuario[]>('/usuarios')
      setUsuarios(Array.isArray(data) ? data : [])
    } catch (e) {
      setError(e instanceof Error ? e.message : 'Não foi possível carregar os usuários.')
    } finally {
      setLoading(false)
    }
  }, [])

  useEffect(() => { void loadUsuarios() }, [loadUsuarios])

  const filtered = useMemo(() => {
    const term = search.trim().toLocaleLowerCase('pt-BR')
    if (!term) return usuarios
    return usuarios.filter(u => u.usuario.toLocaleLowerCase('pt-BR').includes(term) || u.email.toLocaleLowerCase('pt-BR').includes(term) || u.id.toLowerCase().includes(term))
  }, [usuarios, search])

  function openCreate() {
    setEditing(null); setForm(emptyForm); setError(''); setNotice(''); setModalOpen(true)
  }
  function openEdit(usuario: Usuario) {
    setEditing(usuario); setForm({ usuario: usuario.usuario, email: usuario.email, senha: '' }); setError(''); setNotice(''); setModalOpen(true)
  }
  function closeModal() { if (!saving) setModalOpen(false) }

  async function submitForm(event: FormEvent<HTMLFormElement>) {
    event.preventDefault()
    setSaving(true); setError(''); setNotice('')
    try {
      if (editing) {
        const payload: UsuarioForm = { ...form, senha: form.senha }
        await api<Usuario>(`/usuarios/${editing.id}`, { method: 'PUT', body: JSON.stringify(payload) })
        setNotice('Usuário atualizado com sucesso.')
      } else {
        await api<Usuario>('/usuarios', { method: 'POST', body: JSON.stringify(form) })
        setNotice('Usuário cadastrado com sucesso.')
      }
      setModalOpen(false)
      await loadUsuarios()
    } catch (e) {
      setError(e instanceof Error ? e.message : 'Não foi possível salvar o usuário.')
    } finally { setSaving(false) }
  }

  async function deleteUsuario(usuario: Usuario) {
    const confirmed = window.confirm(`Deseja realmente excluir o usuário “${usuario.usuario}”?`)
    if (!confirmed) return
    setDeletingId(usuario.id); setError(''); setNotice('')
    try {
      await api<void>(`/usuarios/${usuario.id}`, { method: 'DELETE' })
      setNotice('Usuário excluído com sucesso.')
      await loadUsuarios()
    } catch (e) {
      setError(e instanceof Error ? e.message : 'Não foi possível excluir o usuário.')
    } finally { setDeletingId(null) }
  }

  return (
    <div className="app-shell">
      <aside className="sidebar">
        <div className="brand"><div className="brand-mark"><Users size={21} /></div><div><strong>Cadastro<span>.</span></strong><small>PAINEL ADMINISTRATIVO</small></div></div>
        <div className="nav-label">GERENCIAMENTO</div>
        <div className="nav-item active"><Users size={18} /><span>Usuários</span><span className="nav-count">{usuarios.length}</span></div>
        <div className="sidebar-bottom"><div className="status-icon"><ShieldCheck size={18} /></div><div><strong>API conectada</strong><small>Spring Boot · PostgreSQL</small></div><span className="online-dot" /></div>
      </aside>

      <main className="main-content">
        <header className="topbar"><div className="breadcrumb">Painel <ChevronRight size={14} /> <strong>Usuários</strong></div><div className="topbar-right"><span className="environment"><span className="online-dot" /> AMBIENTE AWS</span><div className="avatar">VR</div></div></header>
        <section className="page-content">
          <div className="page-heading"><div><div className="eyebrow"><span className="eyebrow-line" /> VISÃO GERAL</div><h1>Usuários</h1><p>Gerencie os usuários cadastrados na sua aplicação.</p></div><button className="primary-button" onClick={openCreate}><CirclePlus size={18} /> Novo usuário</button></div>

          {(error || notice) && <div className={`alert ${error ? 'alert-error' : 'alert-success'}`}><span>{error ? <AlertCircle size={18} /> : <Check size={18} />}</span><p>{error || notice}</p><button aria-label="Fechar mensagem" onClick={() => { setError(''); setNotice('') }}><X size={17} /></button></div>}

          <div className="stats-grid">
            <div className="stat-card"><div className="stat-top"><span>Total de usuários</span><div className="stat-icon purple"><Users size={18} /></div></div><div className="stat-number">{loading ? '—' : usuarios.length}</div><div className="stat-foot"><span className="stat-foot-icon"><Activity size={13} /></span> Registros no banco de dados</div></div>
            <div className="stat-card"><div className="stat-top"><span>Status da API</span><div className="stat-icon green"><Cloud size={18} /></div></div><div className="stat-number status-number"><span className="online-dot" />{error && !usuarios.length ? 'Verificar' : 'Operacional'}</div><div className="stat-foot">Endpoint <code>/usuarios</code></div></div>
            <div className="stat-card"><div className="stat-top"><span>Banco de dados</span><div className="stat-icon blue"><Database size={18} /></div></div><div className="stat-number database-number">PostgreSQL</div><div className="stat-foot"><span className="online-dot" /> Persistência via Amazon RDS</div></div>
          </div>

          <section className="table-card"><div className="table-heading"><div><h2>Todos os usuários</h2><p>Visualize e gerencie os registros cadastrados.</p></div><button className="icon-button" onClick={() => void loadUsuarios()} title="Atualizar lista" disabled={loading}><RefreshCw size={17} className={loading ? 'spin' : ''} /></button></div>
            <div className="table-toolbar"><div className="search-box"><Search size={17} /><input value={search} onChange={e => setSearch(e.target.value)} placeholder="Buscar por nome, e-mail ou ID..." /></div><div className="table-total">{filtered.length} {filtered.length === 1 ? 'registro' : 'registros'}</div></div>
            <div className="table-scroll"><table><thead><tr><th>USUÁRIO</th><th>IDENTIFICADOR</th><th>STATUS</th><th className="actions-head">AÇÕES</th></tr></thead><tbody>
              {loading ? <tr><td colSpan={4}><div className="table-state"><RefreshCw className="spin" size={21} /><span>Carregando usuários...</span></div></td></tr> : error && !usuarios.length ? <tr><td colSpan={4}><div className="table-state"><AlertCircle size={22} /><span>Não foi possível carregar os dados. Verifique a conexão com a API.</span><button className="text-button" onClick={() => void loadUsuarios()}>Tentar novamente</button></div></td></tr> : filtered.length === 0 ? <tr><td colSpan={4}><div className="empty-state"><div className="empty-icon"><Users size={24} /></div><strong>{search ? 'Nenhum resultado encontrado' : 'Nenhum usuário cadastrado'}</strong><p>{search ? 'Tente pesquisar usando outro termo.' : 'Cadastre o primeiro usuário para começar.'}</p>{!search && <button className="primary-button small" onClick={openCreate}><CirclePlus size={16} /> Cadastrar usuário</button>}</div></td></tr> : filtered.map(usuario => <tr key={usuario.id}><td><div className="user-cell"><div className="user-avatar">{usuario.usuario.trim().split(/\s+/).slice(0, 2).map(p => p[0]).join('').toUpperCase()}</div><div className="user-details"><strong>{usuario.usuario}</strong><span>{usuario.email}</span></div></div></td><td><span className="id-text" title={usuario.id}>{usuario.id}</span></td><td><span className="status-pill"><span className="online-dot" /> Ativo</span></td><td><div className="row-actions"><button className="action-button edit" onClick={() => openEdit(usuario)} title="Editar usuário"><Pencil size={16} /></button><button className="action-button delete" onClick={() => void deleteUsuario(usuario)} disabled={deletingId === usuario.id} title="Excluir usuário">{deletingId === usuario.id ? <RefreshCw size={16} className="spin" /> : <Trash2 size={16} />}</button></div></td></tr>)}
            </tbody></table></div><div className="table-footer"><span>Exibindo <strong>{filtered.length}</strong> de <strong>{usuarios.length}</strong> usuários</span><div className="pagination"><button disabled aria-label="Página anterior"><ChevronLeft size={16} /></button><span>1</span><button disabled aria-label="Próxima página"><ChevronRight size={16} /></button></div></div>
          </section>
          <footer className="page-footer"><span>Cadastro de Usuários <span className="footer-dot">·</span> Projeto AWS</span><span className="footer-stack"><span className="online-dot" /> Conectado ao endpoint da API</span></footer>
        </section>
      </main>

      {modalOpen && <div className="modal-backdrop" onMouseDown={e => { if (e.target === e.currentTarget) closeModal() }}><section className="modal" role="dialog" aria-modal="true" aria-labelledby="modal-title"><div className="modal-heading"><div><div className="eyebrow">GERENCIAMENTO</div><h2 id="modal-title">{editing ? 'Editar usuário' : 'Novo usuário'}</h2><p>{editing ? 'Atualize os dados do cadastro.' : 'Preencha os campos para cadastrar um usuário.'}</p></div><button className="icon-button" onClick={closeModal} aria-label="Fechar"><X size={19} /></button></div>
        <form onSubmit={submitForm}><label>Nome do usuário<input required minLength={1} maxLength={100} placeholder="Ex.: Maria Silva" value={form.usuario} onChange={e => setForm({ ...form, usuario: e.target.value })} /></label><label>E-mail<input required type="email" maxLength={100} placeholder="nome@exemplo.com" value={form.email} onChange={e => setForm({ ...form, email: e.target.value })} /></label><label>Senha<input required={!editing} type="password" minLength={5} maxLength={8} placeholder={editing ? 'Informe a nova senha (5 a 8 caracteres)' : 'Entre 5 e 8 caracteres'} value={form.senha} onChange={e => setForm({ ...form, senha: e.target.value })} />{editing && <small className="field-hint">A API pode exigir uma senha ao atualizar. Se deixar vazio, a validação do backend poderá rejeitar a alteração.</small>}</label>
          {error && <div className="form-error"><AlertCircle size={16} />{error}</div>}<div className="modal-actions"><button type="button" className="secondary-button" onClick={closeModal} disabled={saving}>Cancelar</button><button type="submit" className="primary-button" disabled={saving}>{saving ? <><RefreshCw size={16} className="spin" /> Salvando...</> : <><ArrowDownToLine size={16} /> {editing ? 'Salvar alterações' : 'Cadastrar usuário'}</>}</button></div></form>
      </section></div>}
      <div className="api-config"><span className="api-config-dot" /> API: <code>{API_URL}</code></div>
    </div>
  )
}

export default App
